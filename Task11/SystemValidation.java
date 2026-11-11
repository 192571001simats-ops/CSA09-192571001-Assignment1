import parking.model.*;import parking.service.*;import java.time.*;import java.util.*;
public class SystemValidation {
 static final LocalDateTime TIME=LocalDateTime.of(2026,11,11,9,0);
 static void invalid(Runnable r,String label){try{r.run();throw new AssertionError(label);}catch(IllegalArgumentException e){System.out.println("PASS "+label);}}
 public static void main(String[] args){
  ParkingService p=new ParkingService(new int[]{4,2,3});
  var a=p.enter(new TwoWheeler("TN01A1"),TIME,"VISITOR",null,false);
  var b=p.enter(new Car("TN02B2"),TIME,"VISITOR",null,false);
  var c=p.enter(new ElectricCar("TN03C3"),TIME,"VISITOR",null,true);
  var pass=new Pass.Monthly(TIME.toLocalDate(),TIME.toLocalDate().plusMonths(1));
  var d=p.enter(new Car("TN04D4"),TIME,"STAFF",pass,false);
  Bench.require(p.exit(a[0],a[1],TIME.plusMinutes(130),0).doubleValue()==30,"two-wheeler 130 minutes Rs30");
  Bench.require(p.exit(b[0],b[1],TIME.plusMinutes(130),0).doubleValue()==90,"car 130 minutes Rs90");
  Bench.require(p.exit(c[0],c[1],TIME.plusMinutes(130),10).doubleValue()==210,"EV parking plus charging Rs210");
  Bench.require(p.exit(d[0],d[1],TIME.plusMinutes(130),0).doubleValue()==0,"valid monthly staff pass");
  Bench.require(p.collection().doubleValue()==330,"hand calculated daily collection Rs330");
  for(int m:new int[]{0,60,61,480,1440,1441})System.out.println("BOUNDARY minutes="+m+" carFee="+new Car("TN05E5").calculateFee(m));
  Bench.require(new Car("TN05E5").calculateFee(0)==0&&new Car("TN05E5").calculateFee(60)==30&&new Car("TN05E5").calculateFee(61)==60,"zero and slab boundaries");
  invalid(()->ParkingService.create("BOAT","TN06F6"),"unknown vehicle type");
  invalid(()->p.exit(-1,0,TIME,0),"invalid slot");
  ParkingService small=new ParkingService(new int[]{2});
  Bench.require(small.enter(new Car("TN11A1"),TIME,"VISITOR",null,false)!=null,"last suitable slot allocated");
  Bench.require(small.enter(new Car("TN11A2"),TIME,"VISITOR",null,false)==null,"full suitable category handled");
  invalid(()->small.enter(new Car("TN11A1"),TIME,"VISITOR",null,false),"duplicate registration");
  invalid(()->small.exit(0,1,TIME.minusMinutes(1),0),"exit before entry preserves occupied slot");
  Bench.require(small.occupancy()==1,"failed exit does not release slot");
  for(int n:new int[]{20,150,500,10000}){
   String[] keys=new String[n];List<String> list=new ArrayList<>();Map<String,Integer> map=new HashMap<>();
   for(int i=0;i<n;i++){keys[i]="TN"+i;list.add(keys[i]);map.put(keys[i],i);}String target=keys[n-1];
   Bench.measure("array_exit_search",n,()->{long found=0;for(int q=0;q<1000;q++)for(String s:keys)if(s.equals(target)){found++;break;}Bench.sink=found;});
   Bench.measure("arraylist_exit_search",n,()->{long found=0;for(int q=0;q<1000;q++)found+=list.indexOf(target);Bench.sink=found;});
   Bench.measure("hashmap_exit_search",n,()->{long found=0;for(int q=0;q<1000;q++)found+=map.get(target);Bench.sink=found;});
  }
 }
}
