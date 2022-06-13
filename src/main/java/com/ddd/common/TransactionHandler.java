package com.ddd.common;

import com.ddd.common.annotations.SpringComponent;
import com.ddd.common.functionalinterfaces.ThrowableRunnable;
import com.ddd.common.functionalinterfaces.ThrowableSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**<p>Spring @Transactional has a limitation when called from within the same class.
 * <p>Spring AOP injects the transaction when the method call is done outside the class
 * but it's unable to do so when called from within the class because there is no proxy,
 * this utility class helps solve this limitation by wrapping the calls:<p></p>
 * Example of non-working code (child call doesn't use Transactional):</p></p><pre>
 *
 * Transactional
 * public void parentMethod()
 * {    childMethod(); }
 *
 * Transactional
 * public void childMethod()
 * {    doSomething(); }
 * </pre>
 * Example of working code (child call uses Transactional):<pre>
 *
 * Autowired TransactionHandler tra;
 *
 * Transactional
 * public void parentMethod()
 * {    tra.runInTransaction( () -> childMethod() ); }
 *
 * Transactional
 * public void childMethod()
 * {    doSomething(); } </pre>
 *
 * @author Ivan Delgado Huerta */
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Log4j2 @RequiredArgsConstructor @SuppressWarnings("unused")
public class TransactionHandler
{
    @Transactional(
        propagation = REQUIRED,
        rollbackFor = Exception.class)
    public <T> T runInTransaction(ThrowableSupplier<T> supplier) throws Exception
    {   return supplier.get(); }

    @Transactional(
        propagation = REQUIRES_NEW,
        rollbackFor = Exception.class)
    public <T> T runInNewTransaction(ThrowableSupplier<T> supplier) throws Exception
    {   return supplier.get(); }

    @Transactional(
        propagation = REQUIRED,
        rollbackFor = Exception.class)
    public void runInTransaction(ThrowableRunnable runner) throws Exception
    {   runner.run(); }

    @Transactional(
        propagation = REQUIRES_NEW,
        rollbackFor = Exception.class)
    public void runInNewTransaction(ThrowableRunnable runner) throws Exception
    {   runner.run(); }
}