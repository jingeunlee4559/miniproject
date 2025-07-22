package minip.miniproject.model;

public class Menu {
	
	private String m_id ;
	private MenuCategory m_category;
	private String m_name;
	private int m_price;
	private DrinkTemperature m_option;
	
	
	public Menu(String m_id, MenuCategory m_category, String m_name, int m_price, DrinkTemperature m_option) {
		super();
		this.m_id = m_id;
		this.m_category = m_category;
		this.m_name = m_name;
		this.m_price = m_price;
		this.m_option = m_option;
	}


	@Override
    public String toString() {
        // String.format을 사용하여 가격에 3자리마다 콤마(,)를 추가하고 '원'을 붙입니다.
        String formattedPrice = String.format("%,d원", m_price);
        
        // 최종 출력 형식을 지정합니다. 예: [커피] 아이스 아메리카노 - 4,500원 (ICE)
        return String.format(" • [%s] %-15s - %-8s (%s)", m_category, m_name, formattedPrice, m_option);
    }


	public String getM_id() {
		return m_id;
	}


	public MenuCategory getM_category() {
		return m_category;
	}


	public String getM_name() {
		return m_name;
	}


	public int getM_price() {
		return m_price;
	}


	public DrinkTemperature getM_option() {
		return m_option;
	}
	
	
	
	
	
}
