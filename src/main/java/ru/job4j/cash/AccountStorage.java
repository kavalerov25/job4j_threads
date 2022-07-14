package ru.job4j.cash;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountStorage {
    private final Map<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        if (accounts.containsKey(id)) {
            return Optional.of(accounts.get(id));
        }
        return Optional.empty();
    }

    public boolean transfer(int fromId, int toId, int amount) {
        boolean rsl = false;
        Optional<Account> first = getById(fromId);
        Optional<Account> second = getById(toId);
        if (first.isPresent() && second.isPresent()) {
            Account sender = first.get();
            Account recipient = second.get();
            if (sender.amount() - amount >= 0) {
                update(new Account(sender.id(), sender.amount() - amount));
                update(new Account(recipient.id(), sender.amount() + amount));
            }
        }
        return rsl;
    }
}
