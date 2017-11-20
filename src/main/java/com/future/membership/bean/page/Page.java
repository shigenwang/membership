package com.future.membership.bean.page;  
  
/** 
 */  
public class Page {  
    // 分页查询开始记录位置  
    private int begin;  
    // 分页查看下结束位置  
    private int end;  
    // 每页显示记录数  
    private int length;  
    // 查询总记录数  
    private int count;  
    // 当前页码  
    private int current;  
    // 总共页数  
    private int total;  
    
    private int beginPageIndex; // 页码列表的开始索引（包含）
	private int endPageIndex; // 页码列表的结束索引（包含）
  
    public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public Page() {  
    }  
  
    /** 
     * 构造函数 
     *  
     * @param begin 
     * @param length 
     */  
    public Page(int begin, int length) {  
        this.begin = begin;  
        this.length = length;  
        this.end = this.begin + this.length;  
        this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;  
    }  
  
    /** 
     * @param begin 
     * @param length 
     * @param count 
     */  
    public Page(int begin, int length, int count) {  
        this(begin, length);  
        this.count = count;
        this.total = (int) Math.floor((this.count * 1.0d) / this.length);  
        if (this.count % this.length != 0) {  
            this.total++;  
        }
     // 计算总页码
     		// 计算 beginPageIndex 和 endPageIndex
     		// >> 总页数不多于10页，则全部显示
     		if (this.total <= 10) {

     			this.beginPageIndex = 1;
     			this.endPageIndex = this.total;
     		}
     		// >> 总页数多于10页，则显示当前页附近的共10个页码
     		else {
     			// 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
     			this.beginPageIndex = this.current - 4;
     			this.endPageIndex = this.current + 5;
     			// 当前面的页码不足4个时，则显示前10个页码
     			if (this.beginPageIndex < 1) {
     				this.beginPageIndex = 1;
     				this.endPageIndex = 10;
     			}
     			// 当后面的页码不足5个时，则显示后10个页码
     			if (this.endPageIndex > this.total) {
     				this.endPageIndex = this.total;
     				this.beginPageIndex = this.total - 10 + 1;
     			}
     		}
    }  
  
    /** 
     * @return the begin 
     */  
    public int getBegin() {  
        return begin;  
    }  
  
    /** 
     * @return the end 
     */  
    public int getEnd() {  
        return end;  
    }  
  
    /** 
     * @param end 
     *            the end to set 
     */  
    public void setEnd(int end) {  
        this.end = end;  
    }  
  
    /** 
     * @param begin 
     *            the begin to set 
     */  
    public void setBegin(int begin) {  
        this.begin = begin;  
        if (this.length != 0) {  
            this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;  
        }  
    }  
  
    /** 
     * @return the length 
     */  
    public int getLength() {  
        return length;  
    }  
  
    /** 
     * @param length 
     *            the length to set 
     */  
    public void setLength(int length) {  
        this.length = length;  
        if (this.begin != 0) {  
            this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;  
        }  
    }  
  
    /** 
     * @return the count 
     */  
    public int getCount() {  
        return count;  
    }  
  
    /** 
     * @param count 
     *            the count to set 
     */  
    public void setCount(int count) {  
        this.count = count;  
        this.total = (int) Math.floor((this.count * 1.0d) / this.length);  
        if (this.count % this.length != 0) {  
            this.total++;  
        }  
    }  
  
    /** 
     * @return the current 
     */  
    public int getCurrent() {  
        return current;  
    }  
  
    /** 
     * @param current 
     *            the current to set 
     */  
    public void setCurrent(int current) {  
        this.current = current;  
    }  
  
    /** 
     * @return the total 
     */  
    public int getTotal() {  
        if (total == 0) {  
            return 1;  
        }  
        return total;  
    }  
  
    /** 
     * @param total 
     *            the total to set 
     */  
    public void setTotal(int total) {  
        this.total = total;  
    }  
  
}  