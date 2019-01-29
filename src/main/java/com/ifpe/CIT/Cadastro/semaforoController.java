package com.ifpe.CIT.Cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping ("/semaforo/")
public class semaforoController {
	
	@Autowired
	private semaforoService semaforoService;

	@RequestMapping(value="saveList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView salvarPesquisarSemaforo(@Valid @ModelAttribute semaforo semaforo, @RequestParam(value="action", 
		required=false) String action, Errors errors, RedirectAttributes ra) {
		
		if (action != null && action.equals("salvar")) {
			return salvar(semaforo, errors, ra);
		} else {
			return pesquisar(semaforo);
		}
	}
	
	@GetMapping("list")
	public ModelAndView pesquisar(semaforo semaforo) {
		ModelAndView mv = new ModelAndView("cadastros/semaforos-list");
		if (semaforo == null || semaforo.getNome() == null) {
			mv.addObject("lista", semaforoService.listarTodas());	
		} else {
			mv.addObject("lista", semaforoService.buscarPorNomeCurso(semaforo.getNome(), semaforo.getCurso().getId())); // .buscarPorExemplo(semaforo)); // .buscarPorNome(semaforo.getNome()));	
		}
		mv.addObject("listaCursos", cursoService.listarTodos());
		mv.addObject("semaforo", semaforo);
		return mv;
	}

	private ModelAndView salvar(@Valid @ModelAttribute semaforo semaforo, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			ra.addFlashAttribute("mensagemErro", "Não foi possível salvar semaforo: " + errors.getFieldErrors());
		} else {
			try {
				semaforoService.salvar(semaforo);
				ra.addFlashAttribute("mensagemSucesso", "semaforo salva com sucesso [" + semaforo.getNome() + "]");
			} catch (Exception e) {
				ra.addFlashAttribute("mensagemErro", "Não foi possível salvar semaforo: " + e.getMessage());
			}
		}
		return pesquisar(new semaforo());
	}

	@GetMapping("edit/{id}")
	public ModelAndView exibirEdicao(@PathVariable("id") BigInteger id) {
		semaforo semaforo = semaforoService.buscarPorId(id);
		ModelAndView mv = new ModelAndView("Cadastro/semaforo");
		mv.addObject("lista", semaforoService.listarTodas());	
		mv.addObject("semaforo", semaforo);
		return mv;
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") Integer id, RedirectAttributes ra) {
		semaforoService.removerPorId(id);
		ra.addFlashAttribute("mensagemSucesso", "semaforo removida com sucesso");
		return "redirect:/semaforos/list";
	}

}
