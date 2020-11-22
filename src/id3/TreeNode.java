/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.List;


public class TreeNode {

  static String root;
  static HashMap<String, List<String> > parent_child = new HashMap<String, List<String> > () ;
  static HashMap<String, List<String> > relation = new HashMap<String, List<String> > () ;
  
  
  public void TreeNode(){}
  
  public void Set_root(String root){
  
       this.root = root;
  
  }
  
  public String get_root(){
  
      return this.root;
  
  }
  
  
  public List<String> getChild(String parent){
    return parent_child.get(parent);
  }
  
 
   public List<String> getRelation(String parent){
    return relation.get(parent);
  }
 
   
  
  public void add_relation(String parent , List<String> relations ){
     relation.put(parent, relations);
  }
  
  
  
  public void add_parent_child(String parent , List<String> child ){
     parent_child.put(parent, child);
  }
  
  
  
  
}
