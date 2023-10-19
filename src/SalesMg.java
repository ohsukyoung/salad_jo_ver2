import java.io.*;
import java.util.*;

class Menus
{
    public static final int E_CP = 1; // 결제 취소
    public static final int E_REC = 2; // 영수증 출력
    public static final int E_SC = 3; // 매출조회!
    public static final int E_EX = 4; // 종료
}

public class SalesMg
{
    static ArrayList<Receipt> receipts = new ArrayList<>();
    private static BufferedReader br;
    private static Integer sel;

    static
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        sel = 1;
    }

    public static void menuDisp()
    {
        System.out.println("\n\t[ 매출 관리 시스템 ]=============");
        System.out.println("\t1. 결제 취소");
        System.out.println("\t2. 영수증 출력");
        System.out.println("\t3. 매출 조회");
        System.out.println("\t4. 관리자 메뉴 화면으로 이동");
        System.out.println("\t=================================");
        System.out.print("\t▶ 메뉴 선택 : ");
   }

    public static void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    public static void menuRun() throws IOException
    {
        switch (sel)
        {
            case Menus.E_CP : cancelPayment(); break;
            case Menus.E_REC : receipt(); break;
            case Menus.E_SC : salesCheck(); break;
            case Menus.E_EX : exit(); break;
            default:
                System.out.println("\t[!] 메뉴 선택 오류");
        }
    }

    public static void receiptSave(){

        for (Order orders: CacheData.orderOuterList){
            String nowTime = orders.getO_nowTime();
            int year = Integer.parseInt(nowTime.substring(0,4));
            int month = Integer.parseInt(nowTime.substring(4,6));
            int day = Integer.parseInt(nowTime.substring(6,8));
            int hour = Integer.parseInt(nowTime.substring(8,10));
            String memberid = orders.getO_name();
            boolean ismemeber = orders.isMember();
            int usedpoints = orders.getUsedPoints();
            String paymentmethod = orders.getPaymentMethod();
            double totalamout = orders.getO_totPrice();
            receipts.add(new Receipt(year, month, day, hour, memberid, ismemeber, usedpoints, paymentmethod, totalamout));
        }
    }

    public static void cancelPayment() throws IOException
    {
        System.out.println("\n\t[ 결제 취소 ]====================");
        System.out.print("\t▶ 년 입력: ");
        int cancelYear = Integer.parseInt(br.readLine());
        System.out.print("\t▶ 월 입력: ");
        int cancelMonth = Integer.parseInt(br.readLine());
        System.out.print("\t▶ 일 입력: ");
        int cancelDay = Integer.parseInt(br.readLine());
        System.out.println("\t================================");

        boolean foundReceipt = false;

        int receiptIndex = 1;

        System.out.println("\n\t[ 결재취소 리스트 ]");
        System.out.println("\t===========================================================================");
        System.out.printf("\t%-5s \t%5s \t%10s \t%5s \t%5s \t%5s\n","구분번호","회원여부","아이디","포인트사용","결제수단","결재금액");
        System.out.println("\t===========================================================================");
        for (Receipt receipt : receipts)
        {
            // TODO 출력방식 고민
//            ==============================
//            구분번호	회원여부	아이디			포인트사용		결제수단	결제금액
//            1			비회원		회원아님			0			카카오페이		400.0
//            2			회원		01012341234			0			카카오페이		800.0
//
//            구분번호: 1
//            회원 여부: 비회원
//            회원이 아닙니다
//            포인트 사용: 0
//            결제 수단: 카카오페이
//            결제 금액: 400.0
            if (receipt.getYear() == cancelYear && receipt.getMonth() == cancelMonth && receipt.getDay() == cancelDay)
            {
                String isMemberId = "";
                if (receipt.isMember())
                    isMemberId = receipt.getMemberId();
                else
                    isMemberId = "아이디없음";

                System.out.printf("\t%-5d \t%5s \t%16s \t%5s \t%5s \t%5.1f\n",
                        receiptIndex,(receipt.isMember() ? "회원" : "비회원"),isMemberId,receipt.getUsedPoints(),receipt.getPaymentMethod(),receipt.getTotalAmount());
                //System.out.printf("\t%8s %8s %16s %8s %10s %16s","구분번호","회원여부","아이디","포인트사용","결제수단","결재금액");

//                System.out.println("구분번호: " + receiptIndex);
//                System.out.println("회원 여부: " + (receipt.isMember() ? "회원" : "비회원"));
//                if (receipt.isMember())
//                    System.out.println("회원 아이디 : " + receipt.getMemberId());
//                else
//                    System.out.println("회원이 아닙니다");
//                System.out.println("포인트 사용: " + receipt.getUsedPoints());
//                System.out.println("결제 수단: " + receipt.getPaymentMethod());
//                System.out.println("결제 금액: " + receipt.getTotalAmount());
//                System.out.println();
                foundReceipt  = true;
                receiptIndex++;
            }
        }




        if (foundReceipt)
        {

            System.out.print("\t▶ 결제 취소할 구분 번호를 입력하세요: ");
            int selectedReceiptIndex = Integer.parseInt(br.readLine());

            // 사용자가 입력한 번호에 해당하는 결제 내역을 가져옴
            if (selectedReceiptIndex >= 1 && selectedReceiptIndex <= receiptIndex - 1)
            {
                Receipt selectedReceipt = null;
                int currentIndex = 1;

                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == cancelYear && receipt.getMonth() == cancelMonth && receipt.getDay() == cancelDay)
                    {
                        if (currentIndex == selectedReceiptIndex)
                        {
                            selectedReceipt = receipt;
                            break;
                        }
                        currentIndex++;
                    }
                }


                if (selectedReceipt != null)
                {
                    int returnedPoints = selectedReceipt.getUsedPoints();
                    int remainingPoints = (int)(selectedReceipt.getTotalAmount() - selectedReceipt.getUsedPoints());

                    if (selectedReceipt.isMember()==true)
                    {
                        String id = selectedReceipt.getMemberId();
                        int memPoint = MemberMg.hm.get(id).getMemPoint()+returnedPoints-(int)(remainingPoints*0.1);
                        MemberMg.hm.get(id).setMemPoint(memPoint);
                    }


                    // 선택된 결제 내역을 삭제
                    receipts.remove(selectedReceipt);

                    System.out.println("\n\t[ 결제 취소 내역 ]");
                    System.out.println("\t==========================================================================================");
                    System.out.printf("\t%-5s \t%5s \t%10s \t%5s \t%5s \t%5s \t%5s\n","구분번호","회원여부","아이디","포인트사용","결제수단","결제금액","반환된 포인트");
                    System.out.println("\t==========================================================================================");

                    String isMemberId = "";
                    if (selectedReceipt.isMember())
                        isMemberId = selectedReceipt.getMemberId();
                    else
                        isMemberId = "아이디없음";

                    System.out.printf("\t%-5d \t%5s \t%16s \t%5d \t%5s \t%5.1f \t%5d\n",
                            selectedReceiptIndex,(selectedReceipt.isMember() ? "회원" : "비회원"),isMemberId,selectedReceipt.getUsedPoints(),selectedReceipt.getPaymentMethod(),selectedReceipt.getTotalAmount(),returnedPoints);
                    //System.out.printf("\t%8s %8s %16s %8s %10s %16s","구분번호","회원여부","아이디","포인트사용","결제수단","결제금액");

//                    System.out.println("\t- 구분번호: " + selectedReceiptIndex);
//                    System.out.println("\t- 회원 여부: " + (selectedReceipt.isMember() ? "회원" : "비회원"));
//                    if (selectedReceipt.isMember())
//                        System.out.println("\t- 회원 아이디 : " + selectedReceipt.getMemberId());
//                    else
//                        System.out.println("\t- 회원이 아닙니다");
//                    System.out.println("\t- 포인트 사용: " + selectedReceipt.getUsedPoints());
//                    System.out.println("\t- 결제 수단: " + selectedReceipt.getPaymentMethod());
//                    System.out.println("\t- 결제 금액: " + selectedReceipt.getTotalAmount());
//                    System.out.println("\t- 반환된 포인트: " + returnedPoints);
//                    System.out.println("\t---------------------------------------");
                    System.out.println("\t==========================================================================================");

                    System.out.println("\t「 결제가 취소되었습니다. 」");


                }

            }
            else
                System.out.println("\t[!] 올바른 구분 번호를 입력하세요.");
        }

        else
            System.out.println("\t「 해당 일자에 결제 내역이 없습니다. 」");
        System.out.println();
    }



    public static void receipt() throws IOException
    {
        System.out.println("\n\t[ 영수증 출력 ]====================");
        System.out.print("\t▶ 년 입력: ");
        int receiptYear = Integer.parseInt(br.readLine());
        System.out.print("\t▶ 월 입력: ");
        int receiptMonth = Integer.parseInt(br.readLine());
        System.out.print("\t▶ 일 입력: ");
        int receiptDay = Integer.parseInt(br.readLine());
        System.out.print("\t▶ 시간대 입력 : ");
        int receiptHour = Integer.parseInt(br.readLine());
        System.out.println("\t=================================");

        int receiptIndex = 1;

        boolean foundReceipt = false;

        // 해당 일자의 영수증 내역 출력
        System.out.println("\n\t[ 영수증 내역 출력 ]");
        System.out.println("\t=============================================================================================");
        System.out.printf("\t%-5s \t%5s \t%5s \t%5s \t%5s \t%5s\n","구분번호","회원여부","포인트사용","결제수단","결제금액", "실제 결제금액");
        System.out.println("\t=============================================================================================");

        for (Receipt receipt : receipts)
        {
            if (receipt.getYear() == receiptYear && receipt.getMonth() == receiptMonth && receipt.getDay() == receiptDay && receipt.getHour() == receiptHour)
            {

                System.out.printf("\t%-10d \t%5s \t%5d \t%14s \t%7.1f \t%7.1f\n",
                        receiptIndex,(receipt.isMember() ? "회원" : "비회원"),receipt.getUsedPoints(),receipt.getPaymentMethod(),receipt.getTotalAmount(),(receipt.getTotalAmount()-receipt.getUsedPoints()));
                //System.out.printf("\t%8s %8s %16s %8s %10s %16s","구분번호","회원여부","아이디","포인트사용","결제수단","결제금액");


//                System.out.println("구분번호: " + receiptIndex);
//                System.out.println("회원 여부: " + (receipt.isMember() ? "회원" : "비회원"));
//                System.out.println("포인트 사용: " + receipt.getUsedPoints());
//                System.out.println("결제 수단: " + receipt.getPaymentMethod());
//                System.out.println("결제 금액: " + receipt.getTotalAmount());
//                System.out.println("실제 결제 금액: " + (receipt.getTotalAmount()-receipt.getUsedPoints()));
//                System.out.println();
                receiptIndex++;
                foundReceipt  = true;
            }
        }

        // 사용자가 구분번호 입력하면 그 구분번호의 맞는 영수증 내역 출력
        if (foundReceipt)
        {
            System.out.print("\t▶ 출력할 영수증의 구분번호 입력 : ");
            int selectedReceiptIndex = Integer.parseInt(br.readLine());
            System.out.println();

            receiptIndex = 1;

            for (Receipt receipt : receipts)
            {
                if (receipt.getYear() == receiptYear && receipt.getMonth() == receiptMonth && receipt.getDay() == receiptDay && receipt.getHour() == receiptHour)
                {
                    if (receiptIndex == selectedReceiptIndex)
                    {
//                        System.out.println("--------------------------------------");
//                        System.out.println("구분번호: " + receiptIndex);
//                        System.out.println("회원 여부: " + (receipt.isMember() ? "회원" : "비회원"));
//                        System.out.println("포인트 사용: " + receipt.getUsedPoints());
//                        System.out.println("결제 수단: " + receipt.getPaymentMethod());
//                        System.out.println("결제 금액: " + receipt.getTotalAmount());
//                        System.out.println("실제 결제 금액: " + (receipt.getTotalAmount()-receipt.getUsedPoints()));
//                        System.out.println("영수증 출력이 완료 되었습니다~!!!");
//                        System.out.println("--------------------------------------");
//                        System.out.println();

                        System.out.println("\t┌─────────────────────────────────────────────────────┐");
                        System.out.println("\t│                                                     │");
                        System.out.println("\t│  [ 영수증 ] 샐러드먹조                              │");
                        System.out.println("\t│  ---------------------------------------------      │");
                        System.out.printf("\t│  %-8s \t%8s \t%8s         │\n","번호","회원여부","사용포인트");
                        System.out.printf("\t│  %-8d \t%8s \t%8d              │\n",receiptIndex,(receipt.isMember() ? "회원" : "비회원"),receipt.getUsedPoints());
                        System.out.println("\t│  ---------------------------------------------      │");
                        System.out.printf("\t│  %-16s \t%14s   │\n","결제수단",receipt.getPaymentMethod());
                        System.out.printf("\t│  %-16s \t%14.1f        │\n","실제결제금액",receipt.getTotalAmount()-receipt.getUsedPoints());

//                        System.out.printf("\t%-16s \t%14s\n","결제수단","결제금액");
//                        System.out.printf("\t%-16s \t%14.1f\n",receipt.getPaymentMethod(),receipt.getTotalAmount());
                        System.out.println("\t│  ---------------------------------------------      │");
                        System.out.println("\t│  영수증 출력이 완료 되었습니다~!!!                  │");
                        System.out.println("\t│  ---------------------------------------------      │");
                        System.out.println("\t│                                                     │");
                        System.out.println("\t└─────────────────────────────────────────────────────┘");

                        break;
                    }
                    receiptIndex++;
                }
            }
        }

        else
        {
            System.out.println("\t[!] 입력한 날짜와 시간대에 해당하는 영수증이 없습니다.");
            System.out.println();
        }
    }

    public static void salesCheck()throws IOException
    {
        System.out.println("\n\t[ 매출 조회 ]====================");
        System.out.println("\t1. 년 매출 조회");
        System.out.println("\t2. 월 매출 조회");
        System.out.println("\t3. 일 매출 조회");
        System.out.println("\t================================");
        System.out.print("\t▶ 메뉴를 선택하세요: ");
        int reportChoice = Integer.parseInt(br.readLine());

        switch (reportChoice)
        {
            case 1:
                System.out.print("\t▶ 년 입력: ");
                int salesYear = Integer.parseInt(br.readLine());
                System.out.println();
                double YearlySales = 0.0;

                // 연도별 매출 메소드
                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == salesYear)
                    {
                        YearlySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("\t[ %d년 매출 조회 ]---------------------\n", salesYear);
                System.out.println("\t- "+salesYear + "년 매출 현황: " + YearlySales + "원");
                System.out.println("\t「 년 매출 조회가 완료되었습니다. 」");
                System.out.println("\t-------------------------------------");
                System.out.println();
                break;

            case 2:
                System.out.print("\t▶ 년 입력: ");
                int monthlySalesYear = Integer.parseInt(br.readLine());
                System.out.print("\t▶ 월 입력: ");
                int monthlySalesMonth = Integer.parseInt(br.readLine());
                System.out.println();
                double MonthlySales = 0.0;

                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == monthlySalesYear  && receipt.getMonth() == monthlySalesMonth )
                    {
                        MonthlySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("\t[ %d년 %d월 매출 조회 ]-------------------\n", monthlySalesYear, monthlySalesMonth);
                System.out.println("\t- "+monthlySalesYear + "년 " + monthlySalesMonth + "월 매출 현황: " + MonthlySales + "원");
                System.out.println("\t「 월 매출 조회가 완료되었습니다. 」");
                System.out.println("\t-----------------------------------------");
                System.out.println();
                break;

            case 3:
                System.out.print("\t▶ 년 입력: ");
                int dailySalesYear = Integer.parseInt(br.readLine());
                System.out.print("\t▶ 월 입력: ");
                int dailySalesMonth = Integer.parseInt(br.readLine());
                System.out.print("\t▶ 일 입력: ");
                int dailySalesDay = Integer.parseInt(br.readLine());
                System.out.println();
                double dailySales = 0.0;

                // 일별 매출 메소드
                for (Receipt receipt : receipts)
                {
                    if (receipt.getYear() == dailySalesYear && receipt.getMonth() == dailySalesMonth && receipt.getDay() == dailySalesDay)
                    {
                        dailySales += receipt.getTotalAmount();
                    }
                }
                System.out.printf("\t[ %d년 %d월 %d일 매출 조회 ]---------------------\n", dailySalesYear, dailySalesMonth, dailySalesDay);
                System.out.println("\t- "+dailySalesYear + "년 " + dailySalesMonth + "월 " + dailySalesDay + "일 매출 현황: " + dailySales + "원");
                System.out.println("\t「 일 매출 조회가 완료되었습니다. 」");
                System.out.println("----------------------------------------------");
                System.out.println();
                break;

        }
    }

    public static void exit()
    {
        System.out.println();
        System.out.println("\n\t「 관리자 메뉴로 돌아갑니다. 」");
        KioskMg.salesflag = false;
    }

}