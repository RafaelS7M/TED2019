package aplicacao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {
	public static void main(String[] args)  {
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		int escolha;
		int escolhaid;
		String nome;
		String email;
		
		do {
		
			System.out.println("1 – Listar Pessoas cadastradas"); 
			System.out.println("2 – Buscar uma Pessoa pelo id");
			System.out.println("3 – Cadastrar Pessoa");
			System.out.println("4 – Atualizar Pessoa"); 
			System.out.println("5 – Remover uma Pessoa"); 
			System.out.println("0 – Sair");
			
			escolha = scanner.nextInt();
			
			switch (escolha) {
				case 1:
					String jpql = "SELECT p FROM Pessoa p";
					List<Pessoa> pessoas = entityManager.createQuery(jpql, Pessoa.class).getResultList();				
					System.out.println(pessoas);
					break;
				
				case 2:
					System.out.print("digite o id procurado: ");
					escolhaid = scanner.nextInt();
					Pessoa pessoaFound = entityManager.find(Pessoa.class, escolhaid);
					System.out.println(pessoaFound);
					break;
				
				case 3:
					System.out.print("digite o nome da pessoa: ");
					nome = scanner.next();
					System.out.print("digite o email da pessoa: ");
					email = scanner.next();
					Pessoa pessoa = new Pessoa(null, nome, email);
					entityManager.getTransaction().begin();
					entityManager.persist(pessoa);
					entityManager.getTransaction().commit();
					break;
				
				case 4:
					System.out.print("digite o id da pessoa: ");
					escolhaid = scanner.nextInt();
					System.out.print("digite o novo nome: ");
					nome = scanner.next();
					System.out.print("digite o novo email: ");
					email = scanner.next();
					Pessoa pessoaFound2 = entityManager.find(Pessoa.class, escolhaid);
					pessoaFound2.setNome(nome);
					pessoaFound2.setEmail(email);
					entityManager.getTransaction().begin();
					entityManager.persist(pessoaFound2);
					entityManager.getTransaction().commit();
					break;
				
				case 5:
					System.out.print("digite o id da pessoa: ");
					escolhaid = scanner.nextInt();
					Pessoa pessoaFound3 = entityManager.find(Pessoa.class, escolhaid);
					entityManager.getTransaction().begin();
					entityManager.remove(pessoaFound3);
					entityManager.getTransaction().commit();
					break;
				
			}
		} while(escolha != 0);
		
		entityManager.close();
		entityManagerFactory.close();
		scanner.close();
	}
}