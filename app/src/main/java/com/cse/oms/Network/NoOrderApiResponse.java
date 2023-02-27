package com.cse.oms.Network;

public class NoOrderApiResponse {

   String  CustomerId;
   String OrderDate;
   String EntryDateTime;
   int Status;
   int EntryBy;
   String Note;
   int TerritoryId;
   String SCId;
   boolean Success;
   String Message;
   String OrderNo;



   public NoOrderApiResponse(String customerId, String orderDate, String entryDateTime, int status, int entryBy, String note, int territoryId, String SCId) {
      CustomerId = customerId;
      OrderDate = orderDate;
      EntryDateTime = entryDateTime;
      Status = status;
      EntryBy = entryBy;
      Note = note;
      TerritoryId = territoryId;
      this.SCId = SCId;
   }
   public boolean isSuccess() {
      return Success;
   }

   public void setSuccess(boolean success) {
      Success = success;
   }

   public String getMessage() {
      return Message;
   }

   public void setMessage(String message) {
      Message = message;
   }

   public String getOrderNo() {
      return OrderNo;
   }

   public void setOrderNo(String orderNo) {
      OrderNo = orderNo;
   }



   public String getCustomerId() {
      return CustomerId;
   }

   public void setCustomerId(String customerId) {
      CustomerId = customerId;
   }

   public String getOrderDate() {
      return OrderDate;
   }

   public void setOrderDate(String orderDate) {
      OrderDate = orderDate;
   }

   public String getEntryDateTime() {
      return EntryDateTime;
   }

   public void setEntryDateTime(String entryDateTime) {
      EntryDateTime = entryDateTime;
   }

   public int getStatus() {
      return Status;
   }

   public void setStatus(int status) {
      Status = status;
   }

   public int getEntryBy() {
      return EntryBy;
   }

   public void setEntryBy(int entryBy) {
      EntryBy = entryBy;
   }

   public String getNote() {
      return Note;
   }

   public void setNote(String note) {
      Note = note;
   }

   public int getTerritoryId() {
      return TerritoryId;
   }

   public void setTerritoryId(int territoryId) {
      TerritoryId = territoryId;
   }

   public String getSCId() {
      return SCId;
   }

   public void setSCId(String SCId) {
      this.SCId = SCId;
   }
}
