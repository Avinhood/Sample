package com.cts.avin;

import android.content.Context;
import android.inputmethodservice.Keyboard;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cts.avin.data.main.ListData;
import com.cts.avin.data.main.Rows;
import com.cts.avin.network.ApiService;
import com.cts.avin.ui.main.AboutListFragment;
import com.cts.avin.util.ViewModelFactory;
import com.cts.avin.viewmodel.AboutListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AboutListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Mock
    private MutableLiveData<ListData> listData;
    @Mock
    AboutListFragment aboutListFragment;
    @Mock
    ApiService.ApiInterface apiInterface;

    AboutListViewModel mock;
    AboutListViewModel aboutListViewModel;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        ApiService apiService = new ApiService(apiInterface);
        aboutListViewModel = new AboutListViewModel(apiService);

        mock = org.mockito.Mockito.mock(AboutListViewModel.class);
        Mockito.when(mock.getMainListData()).thenReturn(generateAboutListResponse());
    }

    @Test
    public void test_aboutListData() {
        Assert.assertEquals(mock.getMainListData().getValue().getRows().size(), 10);

    }

    private MutableLiveData<ListData> generateAboutListResponse() {
        MutableLiveData<ListData> ldata = new MutableLiveData<>();
        ldata.setValue(new ListData());
        ldata.getValue().setTitle("About app");
        ArrayList<Rows> itemArray = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Rows item = new Rows();
            item.setTitle("Title"+i);
            item.setDescription("Desc"+i);
            item.setImageHref("Image"+i);
            itemArray.add(item);
        }
        ldata.getValue().setRows(itemArray);
        return ldata;
    }

    @Test
    public void whenAboutListIsNotNull() {
        Assert.assertNotNull(mock.getMainListData());

    }

}
