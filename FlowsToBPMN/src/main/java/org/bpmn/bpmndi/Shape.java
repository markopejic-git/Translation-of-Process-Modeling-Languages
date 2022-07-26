package org.bpmn.bpmndi;

import org.bpmn.bpmn_elements.BPMNElement;
import org.bpmn.bpmn_elements.task.Task;
import org.w3c.dom.Element;

import java.util.ArrayList;

import static org.bpmn.steps.BPMN.doc;

public class Shape {

    private String elementId;
    private String elementIdDi;
    private String isHorizontal;
    private Bounds bounds;
    private Element bpmnElement;
    private boolean isSubprocess = false;

    boolean marked;

    BPMNElement associatedBPMNElement;


    public Shape(String elementId, String isHorizontal, Bounds bounds) {

        this.elementId = elementId;
        this.marked = false;
        this.elementIdDi = elementId + "_di";
        this.isHorizontal = isHorizontal;
        this.bounds = bounds;
        this.bpmnElement = doc.createElement("bpmndi:BPMNShape");

    }

    private void setElement() {
        bpmnElement.setAttribute("bpmnElement", elementId);
        bpmnElement.setAttribute("id", elementIdDi);
        bpmnElement.appendChild(bounds.getElementBounds());
    }

    public void setMarked() {
        this.marked = true;
    }

    public boolean getMarked(){
        return this.marked;
    }

    public Shape(String elementId, Bounds bounds) {

        this.elementId = elementId;
        this.marked = false;
        this.elementIdDi = elementId + "_di";
        this.bounds = bounds;
        this.bpmnElement = doc.createElement("bpmndi:BPMNShape");

    }

    private void setElement(boolean expandedSubprocess) {
        bpmnElement.setAttribute("bpmnElement", elementId);
        bpmnElement.setAttribute("id", elementIdDi);
        if (expandedSubprocess) {
            bpmnElement.setAttribute("isExpanded", "true");
        }
        bpmnElement.appendChild(bounds.getElementBounds());
    }

    public Shape(String elementId, Bounds bounds, boolean expandedSubprocess) {

        this.elementId = elementId;
        this.marked = false;
        this.elementIdDi = elementId + "_di";
        this.bounds = bounds;
        this.bpmnElement = doc.createElement("bpmndi:BPMNShape");
        setElement(expandedSubprocess);

    }

    public void setShapeParticipant() {

        bpmnElement.setAttribute("id", elementIdDi);
        bpmnElement.setAttribute("bpmnElement", elementId);
        //bpmnElement.setAttribute("isHorizontal", isHorizontal);
        if (isSubprocess) {
            bpmnElement.setAttribute("isExpanded", "false");
        }

    }

    public void setShapeParticipantIH() {

        bpmnElement.setAttribute("id", elementIdDi);
        bpmnElement.setAttribute("bpmnElement", elementId);
        bpmnElement.setAttribute("isHorizontal", isHorizontal);
        if (isSubprocess) {
            bpmnElement.setAttribute("isExpanded", "false");
        }

    }

    public void setShape() {

        bpmnElement.setAttribute("id", elementIdDi);
        bpmnElement.setAttribute("bpmnElement", elementId);

    }

    public void setBounds() {

        Element elementBounds = bounds.getElementBounds();
        elementBounds.setAttribute("height", String.valueOf(bounds.getHeight()));
        // x, width, height are default values set as attributes of the class FillBPMNDI
        elementBounds.setAttribute("width", String.valueOf(bounds.getWidth()));
        elementBounds.setAttribute("x", String.valueOf(bounds.getX()));
        elementBounds.setAttribute("y", String.valueOf(bounds.getY()));
        bpmnElement.appendChild(elementBounds);

    }

    public Bounds getBounds() {
        return bounds;
    }

    public String getElementId() {
        return elementId;
    }

    public Element getBpmnElement() {
        return bpmnElement;
    }

    @Override
    public String toString() {
        return elementId;
    }
}