package com.delizarov.smartdiet.ui.adapters;


import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.delizarov.smartdiet.ui.viewholders.ViewHolderBase;

public abstract class SortedListAdapter<TItem> extends RecyclerView.Adapter<ViewHolderBase<TItem>> {

    private final SortedList<TItem> _items;

    private Comparator<TItem> _sortComparator;

    public SortedListAdapter(Class<TItem> itemClass, Comparator<TItem> sortComparator) {
        _sortComparator = sortComparator;
        _items = new SortedList<>(itemClass, new SortedListCallback());
    }

    /**
     * Проверка идентичности содержимого двух элементов списка
     *
     * @param oldItem Элемент списка
     * @param newItem Элемент списка
     * @return true, если элементы идентичны
     */
    public boolean areContentsTheSame(TItem oldItem, TItem newItem) {
        return oldItem == null && newItem == null || oldItem != null && oldItem.equals(newItem);
    }

    /**
     * Проверка того, что объекты относятся к одному и тому же элементу списка.
     * Если у элементов существует стабильный уникальный идентификатор, то результатом должно быть сравнение
     * идентичности значений этого идентификатора
     *
     * @param item1 Элемент списка
     * @param item2 Элемент списка
     */
    public boolean areItemsTheSame(TItem item1, TItem item2) {

        return item1.equals(item2);
    }

    /**
     * Возвращает объект сравнения элементов списка для сортировки
     */
    public Comparator<TItem> getSortComparator() {
        return _sortComparator;
    }

    /**
     * Установка объекта сравнения элементов списка для сортировки
     */
    public final void setSortComparator(Comparator<TItem> sortComparator) {
        if (_sortComparator == sortComparator)
            return;
        _sortComparator = sortComparator;
        refresh();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getItemCount() {
        return _items.size();
    }

    /**
     * Возвращает элемент списка в указанной позиции
     *
     * @param position Позиция элемента в списке
     * @return Объект элемента списка
     */
    public TItem get(int position) {
        return _items.get(position);
    }

    /**
     * Добавление элемента списка. Если данный элемент уже представлен в списке ({@code areItemsTheSame}),
     * то он будет заменен на новый
     *
     * @param item Новый элемент списка
     * @return Индекс добавленного элемента
     */
    @SuppressWarnings("UnusedReturnValue")
    public int add(TItem item) {
        return _items.add(item);
    }

    /**
     * Возвращает индекс указанного элемента
     *
     * @param item Элемент списка
     * @return Индекс элемента
     */
    public int indexOf(TItem item) {
        return _items.indexOf(item);
    }

    /**
     * Замена элемента списка в указанной позиции
     *
     * @param index Позиция в списке
     * @param item  Новый элемент списка
     */
    public void updateItemAt(int index, TItem item) {
        _items.updateItemAt(index, item);
    }

    /**
     * Добавление нескольких элементов в список.
     *
     * @param items Новые элементы
     */
    public void addAll(Iterable<TItem> items) {
        _items.beginBatchedUpdates();
        for (TItem item : items) {
            _items.add(item);
        }
        _items.endBatchedUpdates();
    }

    /**
     * Добавление нескольких элементов в список.
     *
     * @param items Новые элементы
     */
    public void addAll(TItem... items) {
        addAll(Arrays.asList(items));
    }

    /**
     * Удаление элемента из списка
     *
     * @param item Удаляемый элемент
     * @return Результат удаление элемента
     */
    public boolean remove(TItem item) {
        return _items.remove(item);
    }

    public void remove(List<TItem> models) {

        _items.beginBatchedUpdates();
        for (TItem model : models) {
            _items.remove(model);
        }
        _items.endBatchedUpdates();
    }

    /**
     * Удаление элемента из списка в указанной позиции
     *
     * @param index Позиция удаляемого элемента
     * @return Удаленный элемент списка
     */
    public TItem removeItemAt(int index) {
        return _items.removeItemAt(index);
    }

    public void replaceAll(List<TItem> models) {

        _items.beginBatchedUpdates();

        for (int i = _items.size() - 1; i >= 0; i--) {
            final TItem model = _items.get(i);
            if (!models.contains(model)) {
                _items.remove(model);
            }
        }

        _items.addAll(models);
        _items.endBatchedUpdates();
    }

    /**
     * Очистка списка элементов
     */
    public void clear() {
        _items.clear();
    }

    /**
     * Обновление позиций элементов в списке
     */
    private void refresh() {
        _items.beginBatchedUpdates();
        for (int i = 0; i < _items.size(); i++) {
            _items.recalculatePositionOfItemAt(i);
        }
        _items.endBatchedUpdates();
    }

    private final class SortedListCallback extends SortedList.Callback<TItem> {

        @Override
        public int compare(TItem o1, TItem o2) {
            if (_sortComparator == null)
                return 0;
            return _sortComparator.compare(o1, o2);
        }

        @Override
        public void onInserted(final int position, final int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(TItem oldItem, TItem newItem) {
            return SortedListAdapter.this.areContentsTheSame(oldItem, newItem);
        }

        @Override
        public boolean areItemsTheSame(TItem item1, TItem item2) {
            return SortedListAdapter.this.areItemsTheSame(item1, item2);
        }
    }
}