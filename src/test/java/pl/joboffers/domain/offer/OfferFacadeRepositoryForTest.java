package pl.joboffers.domain.offer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class OfferFacadeRepositoryForTest implements OfferFacadeRepository {

    Map<String, OfferResponseObject> databaseInMemory = new ConcurrentHashMap<>();


    @Override
    public <S extends OfferResponseObject> S save(final S entity) {
        databaseInMemory.put(entity.linkToOffer(), entity);
        return entity;
    }


    public Optional<OfferResponseObject> findOfferByLinkToOffer(String linkToOffer) {
        return Optional.ofNullable(databaseInMemory.values()
                .stream()
                .filter(offerResponseObject -> offerResponseObject.linkToOffer()
                        .equals(linkToOffer))
                .findAny()
                .orElseThrow(() -> new NoOfferInDBException("No offer in DB")));
    }


    @Override
    public boolean existsById(final String s) {
        return false;
    }

    @Override
    public <S extends OfferResponseObject> List<S> saveAll(final Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<OfferResponseObject> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public List<OfferResponseObject> findAll() {
        return databaseInMemory.values()
                .stream()
                .toList();
    }

    @Override
    public Iterable<OfferResponseObject> findAllById(final Iterable<String> strings) {
        return null;
    }

    @Override
    public List<OfferResponseObject> findAll(final Sort sort) {
        return null;
    }

    @Override
    public Page<OfferResponseObject> findAll(final Pageable pageable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(final String s) {

    }

    @Override
    public void delete(final OfferResponseObject entity) {

    }

    @Override
    public void deleteAllById(final Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(final Iterable<? extends OfferResponseObject> entities) {

    }


    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends OfferResponseObject> S insert(final S entity) {
        return null;
    }

    @Override
    public <S extends OfferResponseObject> List<S> insert(final Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends OfferResponseObject> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends OfferResponseObject> List<S> findAll(final Example<S> example) {
        return null;
    }

    @Override
    public <S extends OfferResponseObject> List<S> findAll(final Example<S> example, final Sort sort) {
        return null;
    }

    @Override
    public <S extends OfferResponseObject> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends OfferResponseObject> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends OfferResponseObject> boolean exists(final Example<S> example) {
        return false;
    }

    @Override
    public <S extends OfferResponseObject, R> R findBy(final Example<S> example,
                                                       final Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}