package jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.sshd.*;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SshTest {

    @Test
    void shouldClone() {
        SshSessionFactory sshSessionFactory = new SshdSessionFactoryBuilder()
                .setPreferredAuthentications("publickey")
                .setHomeDirectory(new File(System.getProperty("user.home")))
                .setSshDirectory(new File(System.getProperty("user.home"), ".ssh"))
                .setKeyPasswordProvider(cp -> new IdentityPasswordProvider(cp) {
                    @Override
                    protected char[] getPassword(URIish uri, String message) {
                        return "xxxx".toCharArray();
                    }
                })
                .build(new JGitKeyCache());

        SshSessionFactory.setInstance(sshSessionFactory);
        String name = System.getProperty("user.name");

        try (Git clonedRepository = Git.cloneRepository()
                .setURI("ssh://" + name + "@nya-gerrit.its.umu.se:29418/nya-deploy")
                .setDirectory(new File("/tmp/slask/nya-baseline"))
                .call()) {

            System.out.println("ok");
        } catch (InvalidRemoteException e) {
            throw new RuntimeException(e);
        } catch (TransportException e) {
            throw new RuntimeException(e);
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }

    }
}
