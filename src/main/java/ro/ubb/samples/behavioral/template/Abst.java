package ro.ubb.samples.behavioral.template;

abstract class Abst {

 void findSolution() {
  step1();
  step2();
  step3();
  step4();
 }
 private void step1() {
  System.out.println(
   "Abst.step1");
 }

 abstract void step2();
 abstract void step3();

 void step4() {
  System.out.println("Abst.step4");
 }
}
