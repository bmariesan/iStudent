package ro.ubb.samples.behavioral.chain_of_responsibility.purchase_power;

class PurchaseRequest {

 private double amount;
 private String purpose;

 public PurchaseRequest(double amount, String purpose) {
  this.amount = amount;
  this.purpose = purpose;
 }

 public double getAmount() {
  return this.amount;
 }

 public void setAmount(double amount) {
  this.amount = amount;
 }

 public String getPurpose() {
  return this.purpose;
 }

 public void setPurpose(String purpose) {
  this.purpose = purpose;
 }
}
