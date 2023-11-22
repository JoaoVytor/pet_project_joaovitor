public class ImagemMovida extends Imagem {
	public void moverDireita(int shift) throws PosicaoInvalidaException {
		if (this.getX() + shift > 1720) { // largura da tela (1920) - largura da imagem (200px)
            throw new PosicaoInvalidaException();
        }
		
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
