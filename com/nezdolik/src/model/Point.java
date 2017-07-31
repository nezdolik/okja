package model;

import java.io.Serializable;
import lombok.Data;


/**
 * Created by nezdolik on 2017-07-31.
 */
@Data
public class Point implements Serializable {

  private final long time;
  private final double value;

}
