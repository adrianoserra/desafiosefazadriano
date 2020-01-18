package desafiosefazadriano.modelo;

public class Telefone {

	private Integer id;
	private int ddd;
	private String numero;
	private String tipo;
	private Integer id_usuario;

	public Telefone(int ddd, String numero, String tipo) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public Telefone(int ddd, String numero, String tipo, Integer id_usuario) {
		super();
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.id_usuario = id_usuario;
	}
	
	public Telefone() {
		super();
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
