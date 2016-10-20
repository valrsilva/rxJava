/**
 * Created by valdeci on 09/09/2016.
 */
public class User {

    private String nome = "";
    private String cargo = "";
    private float salary = 0f;

    public User(String nome, String cargo, float salary){
        this.nome = nome;
        this.cargo = cargo;
        this.salary = salary;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "    {nome:'" + nome + "',cargo:'" + cargo + "',salary:" + salary + "},";
    }
}
