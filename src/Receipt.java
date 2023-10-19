import java.io.Serializable;
import java.util.*;

public class Receipt implements Serializable
{
    private static final long serialVersionUID = 467635289974541449L;

    private int year;				// 년
    private int month;				// 월
    private int day;				// 일	
    private int hour;				// 시간
    private boolean isMember;			// 회원 여부
    private int usedPoints;			// 포인트 사용
    private String paymentMethod;	// 결제 수단
    private double totalAmount;		// 총 결제 금액
    private boolean isCancelled;
    private String memberId;



    // 생성자
    public Receipt(int year, int month, int day, int hour, String memberId, boolean isMember, int usedPoints, String paymentMethod, double totalAmount)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.isMember = isMember;
        this.usedPoints = usedPoints;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.memberId = memberId;
    }

    public Receipt(int year, int month, int day, int hour, String memberId, boolean isMember, int usedPoints, String paymentMethod, double totalAmount, boolean isCancelled)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.isMember = isMember;
        this.usedPoints = usedPoints;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.isCancelled = isCancelled;
        this.memberId = memberId;
    }

    // Getter 및 Setter 메소드 추가

    public int getYear() {return year;}

    public void setYear(int year){this.year = year;}

    public int getMonth() {return month;}

    public void setMonth(int month){this.month = month;}

    public int getDay() {return day;}

    public void setDay(int day) {this.day = day;}

    public int getHour(){return hour;}

    public void setHour(int hour) {this.hour = hour;}

    public boolean isMember() {return isMember;}

    public void setIsMember(boolean isMember) {this.isMember = isMember;}

    public int getUsedPoints() {return usedPoints;}

    public void setUsedPoints(int usedPoints) {this.usedPoints = usedPoints;}

    public String getPaymentMethod() {return paymentMethod;}

    public void setPaymentMethod(String paymentMethod){this.paymentMethod = paymentMethod;}

    public double getTotalAmount() {return totalAmount;}

    public void setTotalAmount(double totalAmount) {this.totalAmount = totalAmount;}

    public boolean isCancelled() {return isCancelled;}

    public String getMemberId() {return memberId;}

    @Override
    public String toString() {
        return "Receipt{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", isMember=" + isMember +
                ", usedPoints=" + usedPoints +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", isCancelled=" + isCancelled +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}