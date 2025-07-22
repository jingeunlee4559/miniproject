package minip.miniproject.model;

public class Menu {

    private String m_id;
    private MenuCategory m_category;
    private String m_name;
    private int m_price;
    private DrinkTemperature m_option;

    public Menu(String m_id, MenuCategory m_category, String m_name, int m_price, DrinkTemperature m_option) {
        this.m_id = m_id;
        this.m_category = m_category;
        this.m_name = m_name;
        this.m_price = m_price;
        this.m_option = m_option;
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("%,d원", m_price);
        return String.format(" • [%s] %-15s - %-8s (%s)", m_category.name(), m_name, formattedPrice, m_option.name());
    }

    // Getters
    public String getM_id() { return m_id; }
    public MenuCategory getM_category() { return m_category; }
    public String getM_name() { return m_name; }
    public int getM_price() { return m_price; }
    public DrinkTemperature getM_option() { return m_option; }
}