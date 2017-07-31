package model;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.transform.Transformer;

/**
 * Created by nezdolik on 2017-07-31.
 */
@Data
@Setter
@RequiredArgsConstructor
public class TimeSeries implements Serializable {

  private static final List<Point> DEFAULT_POINTS_X5 = new ImmutableList.Builder<Point>()
      .add(new Point(1, 1.04)).add(new Point(2, 2.9)).add(new Point(3, 1.74))
      .add(new Point(4, 3.12)).add(new Point(5, 2.65))
      .add(new Point(1, 13.04)).add(new Point(2, 4.92)).add(new Point(3, 1.96))
      .add(new Point(4, 3.44)).add(new Point(5, 2.83))
      .add(new Point(1, 1.49)).add(new Point(2, 2.93)).add(new Point(3, 8.74))
      .add(new Point(4, 2.12)).add(new Point(5, 3.67)).build();

  private final List<Point> data;

  public static TimeSeries defaultX5() {
    return new TimeSeries(DEFAULT_POINTS_X5);
  }

  public static class Matrix2DTransformer {

    //assume data is already time aligned
    public double[][] transform(final TimeSeries series, final int nSamples, final int seasonSize) {
      checkState(series.getData().size() == nSamples * seasonSize);

      final double[][] transformation = new double[nSamples][seasonSize];
      Iterator<Point> pitor = series.data.iterator();

      for (int row = 0; row < nSamples; row++) {
        for (int col = 0; col < seasonSize; col++) {
          {
            transformation[row][col] = pitor.next().getValue();
          }
        }
      }

      return transformation;
    }
  }
}
