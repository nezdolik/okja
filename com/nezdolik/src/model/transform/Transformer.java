package model.transform;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import model.Point;
import model.TimeSeries;
import org.apache.commons.math3.util.DoubleArray;

/**
 * Created by nezdolik on 2017-07-31.
 *
 * currently works only for bi-dimensional space
 */
@RequiredArgsConstructor
public abstract class Transformer {

  final Transformer next;

  public double[][] transform(final TimeSeries series, final int nSamples, final int seasonSize) {
    final double[][] transformed = doTransform(series, nSamples, seasonSize);

    if (next == null) {
      return transformed;
    }

//    final List<Point> transformedPoints = Arrays.stream(transformed).flatMapToDouble(Arrays::stream)
//        .map(val -> new Point(1,val)).collect(Collectors.toList());
    final List<Point> transformedPoints = null;

    return next.transform(new TimeSeries(transformedPoints), nSamples, seasonSize);
  }

  protected abstract double[][] doTransform(final TimeSeries series, final int nSamples,
      final int seasonSize);

}
