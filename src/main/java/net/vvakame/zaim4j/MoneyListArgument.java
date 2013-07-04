package net.vvakame.zaim4j;

/**
 * Argument for Menu list API.
 * @see Zaim.Money#list(MoneyListArgument)
 * @author vvakame
 */
public class MoneyListArgument {

	Long categoryId;

	Long genreId;

	MoneyMode mode;

	Order order;

	String startDate;

	String endDate;

	Integer page;

	Integer limit;


	@Override
	public String toString() {
		return "MoneyListArgument [categoryId=" + categoryId + ", genreId=" + genreId + ", mode="
				+ mode + ", order=" + order + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", page=" + page + ", limit=" + limit + "]";
	}

	/**
	 * @return the categoryId
	 * @category accessor
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 * @category accessor
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the genreId
	 * @category accessor
	 */
	public Long getGenreId() {
		return genreId;
	}

	/**
	 * @param genreId the genreId to set
	 * @category accessor
	 */
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	/**
	 * @return the mode
	 * @category accessor
	 */
	public MoneyMode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 * @category accessor
	 */
	public void setMode(MoneyMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the order
	 * @category accessor
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 * @category accessor
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the startDate
	 * @category accessor
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 * @category accessor
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 * @category accessor
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 * @category accessor
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the page
	 * @category accessor
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 * @category accessor
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the limit
	 * @category accessor
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 * @category accessor
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
