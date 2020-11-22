/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.List;


public class Node {
    String attribute;
    List<String> listofattribute;
    HashMap<String,Integer> frequency;
    HashMap<String,List<String>> frequency_index;
    HashMap<String,List<classification> > classifies;

    public Node(String attribute, List<String> listofattribute, HashMap<String, Integer> frequency, HashMap<String, List<String>> frequency_index, HashMap<String, List<classification>> classifies) {
        this.attribute = attribute;
        this.listofattribute = listofattribute;
        this.frequency = frequency;
        this.frequency_index = frequency_index;
        this.classifies = classifies;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public List<String> getListofattribute() {
        return listofattribute;
    }

    public void setListofattribute(List<String> listofattribute) {
        this.listofattribute = listofattribute;
    }

    public HashMap<String, Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(HashMap<String, Integer> frequency) {
        this.frequency = frequency;
    }

    public HashMap<String, List<String>> getFrequency_index() {
        return frequency_index;
    }

    public void setFrequency_index(HashMap<String, List<String>> frequency_index) {
        this.frequency_index = frequency_index;
    }

    public HashMap<String, List<classification>> getClassifies() {
        return classifies;
    }
    public void setClassifies(HashMap<String, List<classification>> classifies) {
        this.classifies = classifies;
    }
}
