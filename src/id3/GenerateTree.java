/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class GenerateTree {
    Vector listofattributes;
    List<Node> node;
    HashMap<String,Double > information_gain; 
    HashMap<String,String > information_gain_subAttribute; 
    TreeNode treenode = new TreeNode();
    HashMap <String , List<String>>  Main_Attribute = new HashMap <String , List<String>>();
    List<String> sort;
    String decision;

    public GenerateTree( Vector listofattributes, List<Node> node ,  HashMap<String,Double > information_gain ,  HashMap<String,String > information_gain_subAttribute ){
        this.listofattributes = listofattributes;
        this.node = node;
        this.information_gain = information_gain;
        this.information_gain_subAttribute = information_gain_subAttribute;
    }
    
    public void Display_attribute() {
        System.out.println("\nAll attributes of dataset\n");
        for(int i =0 ; i < sort.size(); i++){
            System.out.print((i+1)+" "+sort.get(i)+" ");
        }
    }
    
    public void create_tree() {
        System.out.println("\nGenerating or Creating Tree .... \n");
        sort = new ArrayList();
        double aa =0;
        String loc ="";
         
        // for sorting , finding root
        for(int i = 0 ; i < information_gain.size(); i++ ){
            for(String jj:  information_gain.keySet() ){
               if(!sort.contains(jj)){
                   if(aa < information_gain.get(jj) ){
                       aa = information_gain.get(jj);
//                       System.out.println("aa: " + aa);
                       loc = jj;
//                       System.out.println("loc: " + loc);
                   }
               }
            }
            if(!loc.equals("")){
//                System.out.println("loc: " + loc);
              sort.add(loc);
            }
            aa=0;
            loc = "";
        }
        
       System.out.println(sort);
       // get all main or sub attribute stored
        for(int i = 0 ; i < node.size() ; i++){
            String main = node.get(i).getAttribute();
            List<String> rel = node.get(i).getListofattribute();
            Main_Attribute.put(main, rel);
        }
       
       // set root
       treenode.Set_root(sort.get(0));
       int count =1; 
       // traverse and create tree
       for(int i = 0 ; i < sort.size()-1 ; i++){
       
           String parent =  sort.get(i);
           List<String> rel = Main_Attribute.get(parent);
           List<String> child = new ArrayList<>();
           // setting childs
            for(int j =0 ; j < rel.size(); j++  ){
                if(this.information_gain_subAttribute.get(rel.get(j)).equals("0") ){
                    child.add(information_gain_subAttribute.get((rel.get(j)+"1")));
                }
                else{
                      if(count < sort.size()-1 ){
                        child.add(sort.get(count));
                        count++;
                      }
                      else{
                        child.add(information_gain_subAttribute.get((rel.get(j))));
                      }
                }
           }
           treenode.add_parent_child(parent, child);
           treenode.add_relation(parent, rel);
       }
    }

    public void display_tree(){
        System.out.println("\n\nRoot == "+ treenode.get_root() + "\n");
   	    System.out.println(treenode.get_root());
        gofor_child(treenode.get_root() , "" );
    }
    
    public int gofor_child(String parent, String space) {
    	 List<String> rel = treenode.getRelation(parent);
         List<String> child = treenode.getChild(parent);
    	 if(child == null) {
    		 return 0;
    	 }
    	 else {
    		 int c = rel.size();
    		 for(int i = 0 ; i < c ; i++) {
    			 System.out.println(space+" "+rel.get(i)+":");
    			 System.out.println(space+"   "+child.get(i));
    		     
    			 if(treenode.getChild(child.get(i)) == null )  {}
    			 else {
    				 String temp = space+"     ";
    				 gofor_child( child.get(i) ,  temp );
    			 }
    		 }
    	 }
    	return 0;
    }

    void Decision(String Outlook, int Temp, String Wind, String Date){
        String Temp1;
        if (Temp >= 30){
            Temp1 = "hot";
        } else if (Temp < 30 && Temp >= 20){
            Temp1 = "mild";
        } else {
            Temp1 = "cool";
        }

        ArrayList<String> list = new ArrayList<>();
        list.add(Outlook);
        list.add(Temp1);
        list.add(Wind);
        list.add(Date);

        testTree(treenode.get_root(),list);
        System.out.println("------------------------");
        System.out.println("decision: " + decision);
        System.out.println("------------------------");
    }

    void testTree(String parent, ArrayList<String> list){
        List<String> rel = treenode.getRelation(parent);
        List<String> child = treenode.getChild(parent);

        int stt = -1;
        int remove_list = -1;
        for (int i = 0 ; i < list.size(); i ++){
            for (int j = 0; j < rel.size(); j ++){
                if (list.get(i).equals(rel.get(j))){
                    remove_list = i;
                    stt = j;
                }
            }
        }
        if (remove_list != -1){
            list.remove(remove_list);
        }

        for (int j = rel.size()-1; j >= 0; j --){
            if (stt == -1){
                break;
            } else if (stt != j){
                rel.remove(j);
                child.remove(j);
            }
        }

        int c = rel.size();

        for(int i = 0 ; i < c ; i++) {
            if (child.get(i).equals("no")){
                decision = "no";
            } else if (child.get(i).equals("yes")){
                decision =  "yes";
            }

            if(treenode.getChild(child.get(i)) == null )  {}
            else {
                testTree( child.get(i), list);
            }
        }
    }
}
