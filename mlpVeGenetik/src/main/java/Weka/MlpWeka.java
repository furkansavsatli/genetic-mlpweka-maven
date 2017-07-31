package Weka;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Furkan on 23.07.2017.
 */
public class MlpWeka {

    public static void main(String args[]) {

        try {
            //Reading training arff or csv file
            File directory = new File("./");

            FileReader trainreader = new FileReader(directory.getAbsolutePath()+"\\src\\main\\resources\\titanicarffdata.arff");
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);
            //Instance of NN
            MultilayerPerceptron mlp = new MultilayerPerceptron();

//            mlp.setLearningRate(0.32);
//            mlp.setMomentum(0.4);
//            mlp.setHiddenLayers("17");
              mlp.setLearningRate(0.31);
              mlp.setMomentum(0.29);
              mlp.setHiddenLayers("17");

//            Learning Rate 0.59
//            Momentum Rate 0.55
//            Hidden Layer 27

            mlp.setTrainingTime(100);
            mlp.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(mlp, train);

            System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
            System.out.println(eval.toSummaryString()); //Summary of Training

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
