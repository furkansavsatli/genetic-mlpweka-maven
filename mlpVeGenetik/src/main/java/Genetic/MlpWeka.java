package Genetic;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Furkan on 23.07.2017.
 */
public class  MlpWeka {


    public static int TRANING_TIME = 100;

    public static double mlp( double learningRate, double momentumRate, String hiddenLayers) {

        try {
            File directory = new File("./");

            FileReader trainreader = new FileReader(directory.getAbsolutePath()+"\\src\\main\\resources\\titanicarffdata.arff");
            Instances train = new Instances(trainreader);
            train.setClassIndex(train.numAttributes() - 1);


            MultilayerPerceptron mlp = new MultilayerPerceptron();

            //Setting Parameters
            mlp.setLearningRate(learningRate);
            mlp.setMomentum(momentumRate);
            mlp.setTrainingTime(TRANING_TIME);
            mlp.setHiddenLayers(hiddenLayers);

            mlp.buildClassifier(train);


            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(mlp, train);

            System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
            System.out.println(eval.toSummaryString()); //Summary of Training


            return (eval.pctCorrect());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
      return 0;
    }


}
