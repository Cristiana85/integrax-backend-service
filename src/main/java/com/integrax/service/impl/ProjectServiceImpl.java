package com.integrax.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.integrax.dto.HandleDTO;
import com.integrax.dto.ProjectDTO;
import com.integrax.dto.ResultDTO;
import com.integrax.entity.Project;
import com.integrax.repository.ProjectRepository;
import com.integrax.service.ProjectService;
import com.integrax.util.IntegraxModelMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	@NonNull
	private final ProjectRepository projectRepo;

	private final IntegraxModelMapper modelMapper;

	@PersistenceContext
	private EntityManager em;

	@Override
	@SneakyThrows
	public List<ProjectDTO> getlProject(HandleDTO handle) {
		List<ProjectDTO> lProject = projectRepo.findByAccountId(handle.getAccountId())
				.stream()
				.map(item -> modelMapper.map(item, ProjectDTO.class))
				.collect(Collectors.toList());
		return lProject;
	}


	@Override
	public ProjectDTO getProjectById(Long id) {
		return modelMapper.map(projectRepo.findById(id)
				.orElse(null), ProjectDTO.class);
	}
	
	@Override
	@SneakyThrows
	public ResultDTO<Void> deletelProject(HandleDTO handle, List<ProjectDTO> lProject) {
		projectRepo.deleteAll(modelMapper.mapList(lProject, Project.class));
		return new ResultDTO<>(true);
	}
	
	@Override
	public ResultDTO<ProjectDTO> addProject(HandleDTO handle, ProjectDTO project) {
		ResultDTO<ProjectDTO> ret = new ResultDTO<>(true);
		ProjectDTO projectSaved = saveProject(handle, project);
		ret.setContent(getProjectById(projectSaved.getId()));
		return ret;
	}

	
	@Override
	public void savelProject(HandleDTO handle, List<ProjectDTO> lProject) {
		projectRepo.saveAllAndFlush(modelMapper.mapList(lProject, Project.class));
	}
	
	@Override
	@SneakyThrows
	public ProjectDTO saveProject(HandleDTO handle, ProjectDTO project) {
		Project savedEntity = projectRepo.saveAndFlush(modelMapper.map(project, Project.class));
		ProjectDTO savedDTO = modelMapper.map(projectRepo.findById(savedEntity.getId()).get(), ProjectDTO.class);
		return savedDTO;
	}
	
	@Override
	public ResultDTO<ProjectDTO> editProject(HandleDTO handle, ProjectDTO project) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	@SneakyThrows
	public ResultDTO<ProjectDTO> saveProject(ProjectDTO project) {
		String jsonTxt = new String(Files.readAllBytes(Paths.get("C:\\Users\\crist\\Desktop\\bpp.json")));
		
		JSONObject jsonObject = new JSONObject(jsonTxt);       
		Map<String, Object> r = toMap(jsonObject);
		
		project.setTenantId(1L);
		project.setInfo(r);
		projectRepo.saveAndFlush(modelMapper.map(project, Project.class));
		
		ProjectDTO test = getlProject().getContent().stream().findFirst().get();
		String json = new ObjectMapper().writeValueAsString(test.getInfo());
		test.setJson(json);
		
		return new ResultDTO<>();
	}

	private Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}


	public List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	@Override
	@SneakyThrows
	public ProjectDTO getProjectById(Long id) {
		Optional<ProjectDTO> optionalVersion = projectRepo.findById(id)
				.stream()
				.map(item -> modelMapper.map(item, ProjectDTO.class))
				.findFirst();
		return Optional.ofNullable(optionalVersion.get()).orElseThrow();
	}

	@Override
	public ProjectDTO findByTenantId(@NonNull HandleDTO handle) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
