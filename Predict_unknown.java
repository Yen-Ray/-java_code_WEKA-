
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class Predict_unknown {
	static String currentDirection = new File("").getAbsolutePath();
 
    static int randomSeed=2;
    static int splitnum=66;
    public static void main(String[] args) throws Exception {
    	 String trainDir=currentDirection+"/Train.arff";	
    	 String testDir=currentDirection+"/Test.arff";
    	 
    	 	Classifier j48classfify = new J48();   // Weka decission_tree
           
            Instances train = new Instances(new BufferedReader(new FileReader(trainDir)));
            int lastIndex = train.numAttributes() - 1;
            
	        train.randomize(new Random(randomSeed)); 
	        
	        //--------------------------------------------------------------------// Train part of training data
//	        int trainSize = (int) (train.numInstances() * splitnum / 100); 
//            	Instances TreeTrainNumber = new Instances(train, 0, trainSize);	
//	            TreeTrainNumber.setClassIndex(lastIndex);	           			  // classify type index
//	            j48classfify.buildClassifier(TreeTrainNumber);   
		    //--------------------------------------------------------------------// Train part of training data
	        
	        
	        //--------------------------------------------------------------------// Train all of training data     
	            train.setClassIndex(lastIndex);
	            j48classfify.buildClassifier(train);
	        //--------------------------------------------------------------------// Train all of training data    
	            
            Instances test = new Instances(new BufferedReader(new FileReader(testDir)));
            		  test.setClassIndex(test.numAttributes() - 1);
            
            for(int i=0; i<test.numInstances(); i++){
            	
	            double index = j48classfify.classifyInstance(test.instance(i)); // predict value
	            int out =(int)index;
	           
	              
	              if(out==0) {
	                   	System.out.println("Data-----"+test.instance(i));
	                   	System.out.println("Predict:The weather is Sunny");
	              }else if(out==1) {
	            	  	System.out.println("Data-----"+test.instance(i));
	                   	System.out.println("Predict:The weather is Cloudy");
	              }else {
	            	  	System.out.println("Data-----"+test.instance(i));
	                   	System.out.println("Predict:The weather is Rainy");
	              }
            }
            
    }
   
}
