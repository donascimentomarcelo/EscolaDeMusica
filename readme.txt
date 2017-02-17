Configuração do Spring

1 - Cria uma interface
	Ex: 
		UsuarioService
		
2 - Cria métodos abstratos
	Ex: 
		public void salvar(Usuario usuario);
		public void excluir(Usuario usuario);
		
3 - Cria uma classe
	Ex: 
		UsuarioServiceImpl
		
4 - Implementa o UsuarioService no UsuarioServiceImpl
	Ex: 
		UsuarioServiceImpl implements UsuarioService

5 - Add todas os métodos na classe UsuarioServiceImpl
6 - Coloca a notação @Service("usuarioService") informando que é um serviço
7 - Coloca a notação @Transactional
8 - Faço a injeção do entityManager
	Ex: 
	private EntityManager entityManager;

9 - Defino uma notação para injeção
	Ex: 
	@PersistenceContext
	private EntityManager entityManager;
				
				//**** Bean ****//
10 - Instancia a interface private CursoServico cursoServico;
11 - Coloca uma notação acima da interface informando que é uma injeção real do Spring
	Ex:
	@Autowired
	private CursoServico cursoServico;

				//**** Importante ****//
12 - Acima do public class UsuarioBean, deve ser informado que é um controller/bean do Spring
	 e adiciona o @Scope("session")
	Ex:
	@Controller("cursoBean")
	@Scope("session")
	public class Usuario

	