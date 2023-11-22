import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Principal extends JFrame implements MouseMotionListener {
	
    private static int larguraTela = 1920;
    private static int alturaTela = 900;
	
	private ImagemMovida pokemon;
	private Imagem paisagem;
	private ImagemAnimada pokemonAnimado;
	
	private int mouseX = 0;
	private int mouseY = 0;
	
	public Principal() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseMotionListener(this);
	
		pokemon = new ImagemMovida();
		pokemon.setX(1700);
		pokemon.setY(500);
		pokemon.setImg("pokemon.png");
		
		paisagem = new Imagem();
		paisagem.setX(0);
		paisagem.setY(0);
		paisagem.setImg("paisagem.jpg");
		
		pokemonAnimado = new ImagemAnimada();
		pokemonAnimado.setX(-384); // inicia fora da tela para vir suavemente
		pokemonAnimado.setY(450);
		pokemonAnimado.setImg("pokemon2.png");
	}
	
	//ESSE EH O METODO QUE DEVE SER ADAPTADO AO PROJETO
	public void renderizarImagens(Graphics g2) {
		
		paisagem.desenhar(g2);
		pokemon.desenhar(g2);
		pokemonAnimado.desenhar(g2);
		
		try {
	        if(pokemon.intercepta(pokemonAnimado)) {
	            throw new interceptacao();
	        }
	    } catch (interceptacao e) {
	        mudarYImagemAnimada();
	        pokemon.setImg("pokemon3.png");
	    }
		
	}
	
	//EVITAR ALTERAR ESSE METODO
	public static void main(String[] args) {
		Principal t = new Principal();
		t.setSize(larguraTela, alturaTela);
		t.createBufferStrategy(1);		
		t.setVisible(true);
		t.createBufferStrategy(2);
	}
	
	//EVITAR ALTERAR ESSE METODO
	public void renderizarGraphics() {
		if (!getBufferStrategy().contentsLost()) getBufferStrategy().show();
	    Graphics g = getBufferStrategy().getDrawGraphics();
	    Graphics g2 = g.create();
	    g2.setColor(Color.WHITE);        
	    g2.fillRect(0, 0, getWidth(), getHeight());
	    renderizarImagens(g2);
	    g.dispose(); 
	    g2.dispose();
	}
	
	//EVITAR ALTERAR ESSE METODO
	public void paint(Graphics g) {
		
		// chamo a função para reposionar a imagem a cada atualização de frame :)
		seguirMouseSempre();
		
		// volta imagem animada
		voltarImagemAnimada();
		
		this.renderizarGraphics();
		repaint();
	}
	
	// crio a função de reposionar a imagem, ela está sendo chamada a cada atualização de tela
	private void seguirMouseSempre() {

		// subtraio os valores só pro mouse ficar centralizado na imagem
		if(mouseX - 100 > pokemon.getX()) {
			try {
				pokemon.moverDireita(1);
			} catch (PosicaoInvalidaException e) {
	            System.out.println("Posição Inválida!");
	        }
        } else {
            pokemon.moverEsquerda(1);
        }

        if(mouseY - 140.5 > pokemon.getY()) {
            pokemon.moverBaixo(1);
        } else {
            pokemon.moverCima(1);
        }
		
	}
	
	// caso a imagem animada chegue no final, volte, mas para fora da tela, por isso -(largura da imagem)
	private void voltarImagemAnimada() {
	    if (pokemonAnimado.getX() > larguraTela) { 
	        pokemonAnimado.setX(-384);
	    }
	}
	
	private void mudarYImagemAnimada() {
		pokemonAnimado.setY(50);
	}

	@Override
	public void mouseMoved(MouseEvent mouse) {
		
		// crio mouseX e mouseY para serem usadas fora desse metodo na seguirMouseSempre()
		 mouseX = mouse.getX();
		 mouseY = mouse.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}
}
