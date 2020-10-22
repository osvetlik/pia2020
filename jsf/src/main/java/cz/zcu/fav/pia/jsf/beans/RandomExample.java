package cz.zcu.fav.pia.jsf.beans;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class RandomExample {

	double random = Math.random();

}
