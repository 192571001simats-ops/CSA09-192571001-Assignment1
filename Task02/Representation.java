import java.util.*;
public class Representation {
 public static void main(String[] args){
  int minutes=61;int wrong=minutes/60;long right=(minutes+59L)/60;
  System.out.println("61 minutes: incorrect Rs "+wrong*30+", correct Rs "+right*30);
  Bench.require(right==2,"ceiling billing");
  String[][] floors={new String[20],new String[50],new String[100]};
  Bench.require(Arrays.stream(floors).mapToInt(a->a.length).sum()==170,"unequal floor capacities");
  Bench.require((Integer.MAX_VALUE+59L)/60>0,"addition does not overflow int");
 }
}
