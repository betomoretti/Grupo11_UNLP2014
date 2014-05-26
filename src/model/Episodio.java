package model;

public class Episodio implements Reproducible{
	private int idEpisodio;
	private long duracion;
	private int numero;
	private String titulo;
	private Temporada temporada;
		
	public int getIdEpisodio() {
		return idEpisodio;
	}

	public void setIdEpisodio(int idEpisodio) {
		this.idEpisodio = idEpisodio;
	}

	public Episodio() {}

	public Episodio(long duracion, int numero, String titulo, Temporada temporada){
		this.duracion=duracion;
		this.numero=numero;
		this.titulo=titulo;
		this.temporada=temporada;
		this.temporada.agregarEpisodio(this);
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
	
	public long getDuracion() {
		return duracion;
	}
	
	public void setDuracion( long duracion ) {
		this.duracion = duracion;
	}
	
	public int getEdadMinima(){
		return temporada.getEdadMinima();
	}
	public boolean aptoPara(Usuario usuario){
		return temporada.getSerie().aptoPara(usuario);
	}
	public boolean esPelicula(){
		return false;
	}
	public boolean esEpisodio(){
		return true;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero( int numero ) {
		this.numero = numero;
	}
	
	public Temporada getTemporada() {
		return temporada;
	}
	
	public void setTemporada( Temporada temporada ) {
		this.temporada = temporada;
	}

	
}
