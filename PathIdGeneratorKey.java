package pathIdAllocation;

public class PathIdGeneratorKey {
	public int src;
	public int dst;
	public PathIdGeneratorKey(int src, int dst) {
		this.src = src;
		this.dst = dst;
	}
	public int getSrc() {
		return src;
	}
	public void setSrc(int src) {
		this.src = src;
	}
	public int getDst() {
		return dst;
	}
	public void setDst(int dst) {
		this.dst = dst;
	}
	@Override
	public int hashCode() {
		return (src << 16) + dst;
	}
	@Override
	public boolean equals(Object obj) {
		 if (!(obj instanceof PathIdGeneratorKey)) return false;
		 if (((PathIdGeneratorKey) obj).src != src) return false;
		 if (((PathIdGeneratorKey) obj).dst != dst) return false;
		 return true;
	}
	
}
