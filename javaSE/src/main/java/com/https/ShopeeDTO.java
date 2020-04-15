package com.https;

public class ShopeeDTO {
    private String orderSn;
    private String partner_id;
    private String shopId;
    private long timeStamp;
    private PickUp pickUp;
    private DropOff dropOff;
    private NonIntegrated non_integrated;


    public String getOrderSn() {
        return orderSn;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
    public String getPartner_id() {
        return partner_id;
    }
    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public PickUp getPickUp() {
        return pickUp;
    }
    public void setPickUp(PickUp pickUp) {
        this.pickUp = pickUp;
    }
    public DropOff getDropOff() {
        return dropOff;
    }
    public void setDropOff(DropOff dropOff) {
        this.dropOff = dropOff;
    }
    public NonIntegrated getNon_integrated() {
        return non_integrated;
    }
    public void setNon_integrated(NonIntegrated non_integrated) {
        this.non_integrated = non_integrated;
    }

    @Override
    public String toString() {
        return "com.https.ShopeeDTO{" +
                "orderSn='" + orderSn + '\'' +
                ", partner_id='" + partner_id + '\'' +
                ", shopId='" + shopId + '\'' +
                ", timeStamp=" + timeStamp +
                ", pickUp=" + pickUp +
                ", dropOff=" + dropOff +
                ", non_integrated=" + non_integrated +
                '}';
    }

    public static class PickUp{
        private String address_id;
        private String pick_time_id;
        private String tracking_no;
        public String getAddress_id() {
            return address_id;
        }
        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }
        public String getPick_time_id() {
            return pick_time_id;
        }
        public void setPick_time_id(String pick_time_id) {
            this.pick_time_id = pick_time_id;
        }
        public String getTracking_no() {
            return tracking_no;
        }
        public void setTracking_no(String tracking_no) {
            this.tracking_no = tracking_no;
        }
    }

    public class DropOff{
        private String branch_id;
        private String sender_real_name;
        private String tracking_no;

        public String getBranch_id() {
            return branch_id;
        }
        public void setBranch_id(String branch_id) {
            this.branch_id = branch_id;
        }
        public String getSender_real_name() {
            return sender_real_name;
        }
        public void setSender_real_name(String sender_real_name) {
            this.sender_real_name = sender_real_name;
        }
        public String getTracking_no() {
            return tracking_no;
        }
        public void setTracking_no(String tracking_no) {
            this.tracking_no = tracking_no;
        }
    }

    public class NonIntegrated{
        private String tracking_no;
        public String getTracking_no() {
            return tracking_no;
        }
        public void setTracking_no(String tracking_no) {
            this.tracking_no = tracking_no;
        }
    }

}
