package ro.ubb.samples.behavioral.template;

class Realiz
  extends Specializ {

 protected void step2() {
  System.out.println(
   "Realiz.step2");
 }

 protected void step3_2() {
  System.out.println(
   "Realiz.step3_2");
 }

 protected void step4() {
  System.out.println(
   "Realiz.step4");
  super.step4();
 }
}
