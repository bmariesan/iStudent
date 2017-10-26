package ro.ubb.samples.behavioral.template;

abstract class Specializ
  extends Abst {

 protected void step3() {
  step3_1();
  step3_2();
  step3_3();
 }
 private void step3_1() {
  System.out.println(
   "Specializ.step3_1");
 }
 abstract protected
  void step3_2();

 private void step3_3() {
  System.out.println(
   "Specializ.step3_3");
  }
}
