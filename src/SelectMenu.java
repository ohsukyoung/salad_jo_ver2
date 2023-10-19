// 메뉴 선택
public class SelectMenu extends Super_Select {
    public SelectMenu() {
        this.message    = "\t▶ 메뉴 선택: ";
        this.errorMsg   = "\t[!] 메뉴 리스트 번호에서 벗어났습니다. 다시 입력해주세요.";
//        this.minNum = 1;
    }

    @Override
    public int menuSelect(int listSize,int minNum) {
        super.minNum = minNum;
        return super.menuSelect(listSize,minNum);
    }
}