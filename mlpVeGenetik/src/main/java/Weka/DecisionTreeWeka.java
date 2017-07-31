package Weka;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Şavşatlı on 24.07.2017.
 */
public class DecisionTreeWeka {


    public static void main(String args[]) {


        try {
            //Reading training arff or csv file
            File directory = new File("./");

            FileReader trainreader = new FileReader(directory.getAbsolutePath() + "\\src\\main\\resources\\titanicarffdata.arff");
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);
            //Instance of NN

            FileReader train2reader = new FileReader(directory.getAbsolutePath() + "\\src\\main\\resources\\titanicarffdata.arff");
            Instances train2 = new Instances(train2reader);
            train2.setClassIndex(train.numAttributes() - 1);


            Remove rm = new Remove();
            rm.setAttributeIndices("1");  // remove 1st attribute

            J48 j48 = new J48();
            j48.setUnpruned(true);        // using an unpruned J48
            // meta-classifier
            FilteredClassifier fc = new FilteredClassifier();
            fc.setFilter(rm);
            fc.setClassifier(j48);
            // train and make predictions
            fc.buildClassifier(train);
            for (int i = 0; i < train2.numInstances(); i++) {
                double pred = fc.classifyInstance(train2.instance(i));
                System.out.print("ID: " + train2.instance(i).value(0));
                System.out.print(", actual: " + train2.classAttribute().value((int) train2.instance(i).classValue()));
                System.out.println(", predicted: " + train2.classAttribute().value((int) pred));
            }

//            Evaluation eval = new Evaluation(train);
//            eval.evaluateModel(j48, train);
////
//            System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
//            System.out.println(eval.toSummaryString()); //Summary of Training

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
