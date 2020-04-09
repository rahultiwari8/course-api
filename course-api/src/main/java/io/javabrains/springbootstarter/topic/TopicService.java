package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	private List<Topic> topic=new ArrayList<>(Arrays.asList(
			new Topic("Spring on action description", "Spring name", "SpringID"),
			new Topic("Core Java", "Java", "JavaID"),
			new Topic("Java Script", "Java ScriptName", "JavaScriptID")));
	
	public List<Topic> getAllTopics()
	{
		return topic;
	}
	
	public Topic getTopic(String id)
	{
		return topic.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}

	public void addTopic(Topic topic) {
		
		this.topic.add(topic);		
	}

	public void updateService(String id,Topic utopic) {
		
		for (int i = 0; i < topic.size(); i++) {
			Topic ttopic=topic.get(i);
			if(ttopic.getId().equals(id))
			{
				topic.set(i, utopic);
				return;
			}
			
		}
		
		
		
	}

	public void deleteService(String id) {
		
		topic.removeIf(t -> t.getId().equals(id));
		
	}

	
}
