package ro.ubb.samples.behavioral.chain_of_responsibility.purchase_power;

class ManagerPPower extends PurchasePower {

 protected double getAllowable() {
  return BASE * 10;
 }

 protected String getRole() {
  return "Manager";
 }
}

class DirectorPPower extends PurchasePower {

 protected double getAllowable() {
  return BASE * 20;
 }

 protected String getRole() {
  return "Director";
 }
}

class VicePresidentPPower extends PurchasePower {

 protected double getAllowable() {
  return BASE * 40;
 }

 protected String getRole() {
  return "Vice President";
 }
}

class PresidentPPower extends PurchasePower {

 protected double getAllowable() {
  return BASE * 60;
 }

 protected String getRole() {
  return "President";
 }
}
