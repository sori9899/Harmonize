package kr.ac.chungbuk.harmonize.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import kr.ac.chungbuk.harmonize.R;
import kr.ac.chungbuk.harmonize.item.MusicListItemView;
import kr.ac.chungbuk.harmonize.model.Music;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView musicListView;

    public MusicListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicListFragment newInstance(String param1, String param2) {
        MusicListFragment fragment = new MusicListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        musicListView = view.findViewById(R.id.musicListView);

        MusicListAdapter adapter = new MusicListAdapter();
        adapter.addItem(new Music(
                1L, "사건의 지평선", "윤하", "https://search.pstatic.net/common?type=n&size=174x174&quality=95&direct=true&src=https%3A%2F%2Fmusicmeta-phinf.pstatic.net%2Falbum%2F007%2F434%2F7434553.jpg%3Ftype%3Dr204Fll%26v%3D20230109102326",
                2, 79, false, 1L));

        musicListView.setAdapter(adapter);
    }

    class MusicListAdapter extends BaseAdapter {
        ArrayList<Music> musics = new ArrayList<>();

        @Override
        public int getCount() {
            return musics.size();
        }

        public void addItem(Music music) {
            musics.add(music);
        }

        @Override
        public Object getItem(int position) {
            return musics.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Music music = musics.get(position);

            MusicListItemView view = new MusicListItemView(getActivity().getApplicationContext());
            view.setNameAndArtist(music.music_name, music.artist, music.level, music.range_avg);

            if (music.img_link != null) {
                ImageView thumbnailView = view.findViewById(R.id.thumbnailView);
                Glide
                        .with(getActivity())
                        .load(music.img_link)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .placeholder(new ColorDrawable(Color.parseColor("#eeeeee")))
                        .into(thumbnailView);
            }

            return view;
        }
    }
}