public class Piece{
	private boolean isFire;
	private Board b;
	private int x;
	private int y;
	private String type;
	private boolean capture= false;
	private Piece moving_p;

	public Piece(boolean isFire, Board b, int x, int y, String type){
		this.isFire=isFire;
		this.b=b;
		this.x=x;
		this.y=y;
		this.type=type;
	}

	public boolean isFire(){
		return isFire;
	}

	public int side(){
		if (isFire){
			return 0;
		}
		else{
			return 1;
		}
	}

	public boolean isKing(){
		if((isFire && (y==7))||(!isFire && (y==0))) {
			return true;
		}
		return false;
	}

	public boolean isBomb(){
		if(type=="bomb"){
			return true;
		}
		return false;
	}
	public boolean isShield(){
		if(type=="shield"){
			return true;
		}
		return false;
	}
	public boolean hasCaptured(){
		return capture;
	}
	public void doneCapturing(){
		capture=false;
	}

	private void remove_s(int xc, int yc){
		for(int i=xc-1; i<xc+2;i++){
			for(int j=yc-1; j<yc+2;j++){
				if ((b.pieceAt(i,j)!=null)&&(!b.pieceAt(i,j).isShield())) {
					b.remove(i,j);
				}
			}
		}
	}

	
	public void move(int xmove, int ymove){
		if (isBomb()){
			if (Math.abs(xmove-x)==2){
				b.remove((x+xmove)/2, (y+ymove)/2);
				b.remove(x,y);
				capture=true;
				remove_s(xmove,ymove);
			}
			else{
				moving_p= b.remove(x,y);
		        b.place(moving_p,xmove,ymove);
		        this.x=xmove;
		        this.y=ymove;
			}
		}

		else{
		if (Math.abs(xmove-x)==2){
			b.remove((x+xmove)/2, (y+ymove)/2);
			capture=true;
		}
		moving_p= b.remove(x,y);
		b.place(moving_p,xmove,ymove);
		this.x=xmove;
		this.y=ymove;
	}

	}



}