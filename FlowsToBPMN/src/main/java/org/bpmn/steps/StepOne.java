package org.bpmn.steps;

import org.bpmn.bpmn_elements.Loop;
import org.bpmn.bpmn_elements.dataobject.DataObject;
import org.bpmn.bpmn_elements.flows.SequenceFlow;
import org.bpmn.bpmn_elements.gateway.ExclusiveGateway;
import org.bpmn.bpmn_elements.gateway.Predicate;
import org.bpmn.bpmn_elements.task.Task;
//import org.bpmn.bpmndi.FillBPMNDI;
import org.bpmn.bpmndi.FillBPMNDI;
import org.bpmn.flows_objects.AbstractObjectType;
import org.bpmn.process.FlowsProcessObject;
import org.bpmn.randomidgenerator.RandomIdGenerator;
import org.bpmn.bpmn_elements.collaboration.Collaboration;
import org.bpmn.bpmn_elements.collaboration.participant.Object;
import org.w3c.dom.Element;

import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import static org.bpmn.steps.BPMN.*;
import static org.bpmn.bpmn_elements.collaboration.Collaboration.objects;

public class StepOne implements Step{
    ExecStep step;
    String file;
    Element definitionsElement;
    HashMap<Double, ArrayList<AbstractObjectType>> objectTypeObjects;

    public static ArrayList<Object> allParticipants = new ArrayList();
    public static ArrayList<Task> allTasks = new ArrayList();

    public static ArrayList<DataObject> allDataObjects = new ArrayList();
    public static ArrayList<SequenceFlow> allFlows = new ArrayList();
    public static ArrayList<ExclusiveGateway> allGateways = new ArrayList();
    public static HashSet<Loop> allLoops = new HashSet<>();
    public static ArrayList<Predicate> predicates = new ArrayList<>();
    static String bpmnDiagramID = "BPMNDiagram_" + RandomIdGenerator.generateRandomUniqueId(6);

    private Collaboration collaboration;

    public StepOne(String file, Element definitionsElement, HashMap<Double, ArrayList<AbstractObjectType>> objectTypeObjects) {
        this.file = file;
        this.definitionsElement = definitionsElement;
        this.objectTypeObjects = objectTypeObjects;
        this.step = ExecStep.ONE;
    }

    public void execute() throws TransformerException {

        boolean adHoc = true;
        this.collaboration = new Collaboration();
        collaboration.setParticipants(objectTypeObjects, adHoc);
        Element collaborationElement = collaboration.getElementCollaboration();

        definitionsElement.appendChild(collaborationElement);
        setProcesses(definitionsElement);

        FillBPMNDI di = new FillBPMNDI();
        di.fillBPMNDI(bpmnDiagramID, definitionsElement, collaboration, false, false, true);

        createXml(file);

    }

     public void setProcesses(Element definitionsElement) {

        for (Object participant : objects) {

            participant.getProcessRef().setElementFlowsProcess();
            definitionsElement.appendChild(participant.getProcessRef().getElementFlowsProcess());

        }
         FlowsProcessObject.resetCountProcess();
    }

    public Collaboration getCollaboration() {
        return collaboration;
    }
}
