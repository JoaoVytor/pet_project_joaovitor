public class ImagemMovida extends Imagem {
	public void moverDireita(int shift) {
		this.setX(this.getX() + shift);
	}
	
	public void moverEsquerda(int shift) {
		this.setX(this.getX() - shift);
	}
	
	public void moverCima(int shift) {
		this.setY(this.getY() - shift);
	}
	
	public void moverBaixo(int shift) {
		this.setY(this.getY() + shift);
	}
}
