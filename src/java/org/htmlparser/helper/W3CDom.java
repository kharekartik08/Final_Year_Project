package org.htmlparser.helper;

import org.htmlparser.nodes.Attribute;
import org.htmlparser.select.NodeTraversor;
import org.htmlparser.select.NodeVisitor;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import javax.xml.transform.TransformerException;

/**
 * Helper class to transform a {@link org.htmlparser.nodes.Document} to a {@link org.w3c.dom.Document org.w3c.dom.Document},
 * for integration with toolsets that use the W3C DOM.
 * <p>
 * This class is currently <b>experimental</b>, please provide feedback on utility and any problems experienced.
 * </p>
 */
public class W3CDom {
    protected DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    /**
     * Convert a jsoup Document to a W3C Document.
     * @param in jsoup doc
     * @return w3c doc
     */
    public Document fromJsoup(org.htmlparser.nodes.Document in) {
        Validate.notNull(in);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document out = builder.newDocument();
            convert(in, out);
            return out;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Converts a jsoup document into the provided W3C Document. If required, you can set options on the output document
     * before converting.
     * @param in jsoup doc
     * @param out w3c doc
     * @see org.htmlparser.helper.W3CDom#fromJsoup(org.htmlparser.nodes.Document)
     */
    public void convert(org.htmlparser.nodes.Document in, Document out) {
        if (!StringUtil.isBlank(in.location()))
            out.setDocumentURI(in.location());

        org.htmlparser.nodes.Element rootEl = in.child(0); // skip the #root node
        NodeTraversor traversor = new NodeTraversor(new W3CBuilder(out));
        traversor.traverse(rootEl);
    }

    /**
     * Implements the conversion by walking the input.
     */
    protected class W3CBuilder implements NodeVisitor {
        private final Document doc;
        private Element dest;

        public W3CBuilder(Document doc) {
            this.doc = doc;
        }

        public void head(org.htmlparser.nodes.Node source, int depth) {
            if (source instanceof org.htmlparser.nodes.Element) {
                org.htmlparser.nodes.Element sourceEl = (org.htmlparser.nodes.Element) source;
                Element el = doc.createElement(sourceEl.tagName());
                copyAttributes(sourceEl, el);
                if (dest == null) { // sets up the root
                    doc.appendChild(el);
                } else {
                    dest.appendChild(el);
                }
                dest = el; // descend
            } else if (source instanceof org.htmlparser.nodes.TextNode) {
                org.htmlparser.nodes.TextNode sourceText = (org.htmlparser.nodes.TextNode) source;
                Text text = doc.createTextNode(sourceText.getWholeText());
                dest.appendChild(text);
            } else if (source instanceof org.htmlparser.nodes.Comment) {
                org.htmlparser.nodes.Comment sourceComment = (org.htmlparser.nodes.Comment) source;
                Comment comment = doc.createComment(sourceComment.getData());
                dest.appendChild(comment);
            } else if (source instanceof org.htmlparser.nodes.DataNode) {
                org.htmlparser.nodes.DataNode sourceData = (org.htmlparser.nodes.DataNode) source;
                Text node = doc.createTextNode(sourceData.getWholeData());
                dest.appendChild(node);
            } else {
                // unhandled
            }
        }

        public void tail(org.htmlparser.nodes.Node source, int depth) {
            if (source instanceof org.htmlparser.nodes.Element && dest.getParentNode() instanceof Element) {
                dest = (Element) dest.getParentNode(); // undescend. cromulent.
            }
        }

        private void copyAttributes(org.htmlparser.nodes.Node source, Element el) {
            for (Attribute attribute : source.attributes()) {
                el.setAttribute(attribute.getKey(), attribute.getValue());
            }
        }
    }

    /**
     * Serialize a W3C document to a String.
     * @param doc Document
     * @return Document as string
     */
    public String asString(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException e) {
            throw new IllegalStateException(e);
        }
    }
}
