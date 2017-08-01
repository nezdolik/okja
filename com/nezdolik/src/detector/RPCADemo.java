package detector;

import model.TimeSeries;
import org.apache.commons.math3.linear.RealMatrix;
import org.surus.math.RPCA;

/**
 * Robust Principal Component Analysis on time series demo
 *
 * Created by nezdolik on 2017-07-31.
 *
 * {@link https://arxiv.org/pdf/1608.02148.pdf}
 * {@link https://github.com/Netflix/Surus}
 */
public class RPCADemo {

  private static final double LPENALTY_DEFAULT = 1d;
  private static final double RPENALTY_DEFAULT = 1d;

  public static void main(String[] args) {
    final TimeSeries.Matrix2DTransformer transformer = new TimeSeries.Matrix2DTransformer();
    double[][] data = transformer.transform(TimeSeries.defaultX5(), 3, 5);//5 per season

    //standardizeFeatures(data);

    RPCA rpca = new RPCA(data, LPENALTY_DEFAULT, RPENALTY_DEFAULT);
    RealMatrix L = rpca.getL();
    RealMatrix S = rpca.getS();
    RealMatrix E = rpca.getE();
    System.out.println("L: " + L);//low-rank component
    System.out.println("S: " + S);//sparse component
    System.out.println("E: " + E);//noise

  }

  /**
   * zero mean, unit variance
   *
   * rpca uses specific epsilons and expects value range to be standardized
   */
  private static void standardizeFeatures(final double[][] data) {
    double mean = mean(data);
    double std = std(data, mean);

    for (int row = 0; row < data.length; row++) {
      for (int col = 0; col < data[0].length; col++) {
        data[row][col] = (data[row][col] - mean) / std;
      }
    }

  }

  private static double std(double[][] data, final double mean) {
    double std = 0;

    for (int row = 0; row < data.length; row++) {
      for (int col = 0; col < data[0].length; col++) {
        std += Math.pow(data[row][col] - mean, 2) ;
      }
    }

    Math.sqrt(std / (data.length * data[0].length - 1));

    return std;
  }

  private static double mean(final double[][] data) {
    double mean = 0;
    for (int row = 0; row < data.length; row++) {
      for (int col = 0; col < data[0].length; col++) {
        mean += data[row][col];
      }
    }

    mean /= data.length * data[0].length;

    return mean;
  }

}
