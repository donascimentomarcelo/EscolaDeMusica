package escola.musica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import escola.musica.modelo.Cidade;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String valor) {
		if(valor.equals("")|| !valor.contains("#"))
		{
			return null;
		}
		
		Cidade cidade =  new Cidade();
		String[] propriedades = valor.split("#"); 
		
		if(!propriedades[0].isEmpty())
		{
			cidade.setId(new Integer(propriedades[0]));
		}
		
		if(!propriedades[1].isEmpty())
		{
			cidade.setNome(propriedades[1]);
		}
		
		return cidade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj == null || !(obj instanceof Cidade))
		{
			return "";
		}
		
		Cidade cidade = (Cidade) obj;
		
		String id = cidade.getId() == null ? "" : cidade.getId().toString();
		//se cidade.getId() for igual a nulo, ID recebe uma string vazia se não recebe o ID da cidade
		//chama o toString() pq id é inteiro
		String nome = cidade.getNome() == null ? "" : cidade.getNome();
		// o : representa um else
		return id + "#" + nome;
		//id é a posição 0 do array e nome a posição 1. são referenciados no método acima no if. 
	}

}
