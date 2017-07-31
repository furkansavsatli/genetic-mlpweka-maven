package Weka;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.WekaPackageManager;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Şavşatlı on 24.07.2017.
 */
public class SVMWeka {


    public static void main(String args[]) {


        WekaPackageManager.loadPackages(false, true, false);
        try {
            File directory = new File("./");

            FileReader trainreader = new FileReader(directory.getAbsolutePath()+"\\src\\main\\resources\\titanicarffdata.arff");
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);


            weka.classifiers.functions.SMO scheme = new weka.classifiers.functions.SMO();
            // set options
            scheme.setOptions(weka.core.Utils.splitOptions("-C 1.0 -L 0.0010 -P 1.0E-12 -N 0 -V -1 -W 1 -K \"weka.classifiers.functions.supportVector.NormalizedPolyKernel -C 250007 -E 1.0\""));


            scheme.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(scheme, train);

            System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
            System.out.println(eval.toSummaryString()); //Summary of Training

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
