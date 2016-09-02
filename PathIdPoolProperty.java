package pathIdAllocation;

public class PathIdPoolProperty {
	public int poolNum; //start with 0
	public int poolValue; //0 means available
	public PathIdPoolProperty(int poolNum, int poolValue) {
		this.poolNum = poolNum;
		this.poolValue = poolValue;
	}
	public PathIdPoolProperty() {
	}
	public int getPoolNum() {
		return poolNum;
	}
	public void setPoolNum(int poolNum) {
		this.poolNum = poolNum;
	}
	public int getPoolValue() {
		return poolValue;
	}
	public void setPoolValue(int poolValue) {
		this.poolValue = poolValue;
	}
}