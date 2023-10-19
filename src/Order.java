
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.StringTokenizer;

import java.util.Date;

/*
 주문 ----------------------------------------------------------------
*/
class OrderValues implements Serializable{
    private static final long serialVersionUID = -9194797724974080745L;
    private int u_checkNumber;  // 인덱스넘버
    private String u_name;  // 제품
    private int u_Count;    // 수량
    private int u_calorie;  // 칼로리
    private int u_price;    // 금액

    OrderValues(String u_name, int u_Count, int u_calorie, int u_price) {
        this.u_name = u_name;
        this.u_Count = u_Count;
        this.u_calorie = u_calorie;
        this.u_price = u_price;
    }

    OrderValues(int u_checkNumber, String u_name, int u_Count, int u_calorie, int u_price) {
        this.u_checkNumber = u_checkNumber;
        this.u_name = u_name;
        this.u_Count = u_Count;
        this.u_calorie = u_calorie;
        this.u_price = u_price;
    }

    public OrderValues() {}

    public int getU_checkNumber() { return u_checkNumber; }
    public void setU_checkNumber(int u_checkNumber) { this.u_checkNumber = u_checkNumber; }
    public String getName() { return u_name; }
    public void setU_name(String u_name) { this.u_name = u_name; }
    public int getCount() { return u_Count; }
    public void setU_Count(int u_Count) { this.u_Count = u_Count; }
    public int getCalorie() { return u_calorie; }
    public void setU_calorie(int u_calorie) { this.u_calorie = u_calorie; }
    public int getPrice() { return u_price; }
    public void setU_price(int u_price) { this.u_price = u_price; }

    @Override
    public String toString() {
        return "선택내용" + "{" + "상품명 : " + u_name +
                ", 선택개수 : " + u_Count +
                ", 칼로리 : " + u_calorie * u_Count +
                ", 가격 : " + u_price * u_Count + "}";
    }
}

public class Order implements Serializable{

    private static final long serialVersionUID = 4125292916481512201L;

    private String o_name;  // 회원아이디
    private String o_nowTime;   // 결재시간

    List<OrderValues> innerList = new ArrayList<OrderValues>();
    //    UserProduct o_userPd;
    private int o_totCalorie;
    private int o_totPrice;

    private boolean isMember;
    private int usedPoints;			// 포인트 사용
    private String paymentMethod;	// 결제 수단
    private double totalAmount;		// 총 결제 금액
    private boolean isCancelled;
    

    Order(String o_name) {
        this.o_name = o_name;
    }

    Order(String o_name, String o_nowTime, int o_totCalorie, int o_totPrice) {
        this.o_name = o_name;
        this.o_nowTime = o_nowTime;
        this.o_totCalorie = o_totCalorie;
        this.o_totPrice = o_totPrice;
    }

    Order(String o_name, String o_nowTime, List<OrderValues> userList, int o_totCalorie, int o_totPrice) {
        this.o_name = o_name;
        this.o_nowTime = o_nowTime;
        this.innerList = userList;
//        this.o_userPd = o_salad;
        this.o_totCalorie = o_totCalorie;
        this.o_totPrice = o_totPrice;
    }

    public Order() { }

    public String getO_name() {
        return o_name;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getO_nowTime() {
        return o_nowTime;
    }

    public void setO_nowTime(String o_nowTime) {
        this.o_nowTime = o_nowTime;
    }

    public List<OrderValues> getO_userList() {
        return innerList;
    }

    public void setO_userList(List<OrderValues> o_userList) {
        this.innerList = innerList;
    }

    public int getO_totCalorie() {
        return o_totCalorie;
    }

    public void setO_totCalorie(int o_totCalorie) {
        this.o_totCalorie = o_totCalorie;
    }

    public int getO_totPrice() {
        return o_totPrice;
    }

    public void setO_totPrice(int o_totPrice) {
        this.o_totPrice = o_totPrice;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public int getUsedPoints() {
        return usedPoints;
    }

    public void setUsedPoints(int usedPoints) {
        this.usedPoints = usedPoints;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_name='" + o_name + '\'' +
                ", o_nowTime='" + o_nowTime + '\'' +
                ", innerList=" + innerList +
                ", o_totCalorie=" + o_totCalorie +
                ", o_totPrice=" + o_totPrice +
                ", isMember=" + isMember +
                ", usedPoints=" + usedPoints +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", isCancelled=" + isCancelled +
                '}';
    }
}

class OrderCart {
    public String nowTime() {   // 현재시간
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmmss"; //hhmmss로 시간,분,초만 뽑기도 가능
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
//        System.out.println(formatter.format(today));
        return formatter.format(today);
    }
}

